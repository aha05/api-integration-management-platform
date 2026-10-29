package com.apibridge.integration.integration.auth;


import com.apibridge.integration.integration.user.dto.UserResponse;
import com.apibridge.integration.integration.user.mapper.UserMapper;
import com.apibridge.integration.integration.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtConfig jwtConfig;

//    @PostMapping("/register")
//    public ResponseEntity<UserResponse> registerUser(
//            @Valid @RequestBody UserRequest request,
//            UriComponentsBuilder uriBuilder) {
//        if(userRepository.existsByEmail(request.getEmail())){
//            throw new ResourceAlreadyExistException("User", "email", request.getEmail());
//        }
//
//        var user = userMapper.toEntity(request);
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        userRepository.save(user);
//
//        var userDto = userMapper.toDto(user);
//        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
//        return ResponseEntity.created(uri).body(userDto);
//    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response
    ){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        var cookie = new Cookie("refreshToken", refreshToken.toString()); //change: convert to string and return

        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(accessToken.toString())); //change: convert to string and return
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(
            @CookieValue(value = "refreshToken") String refreshToken){
        var jwt = jwtService.parseToken(refreshToken);
        if(jwt == null || jwt.isExpired()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var user = userRepository.findById(jwt.getUserId()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);

        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(accessToken.toString())); //change: convert to string and return

    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = Long.valueOf(authentication.getPrincipal().toString());

        var user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        // Clear HttpOnly cookie
        System.out.println("Logout");
        var cookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}


