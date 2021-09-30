package com.austin.smartschoolapi.service.auth;

import com.austin.smartschoolapi.Dto.LoginRequestDto;
import com.austin.smartschoolapi.Dto.TokenRequestDto;
import com.austin.smartschoolapi.Dto.UserDto;
import com.austin.smartschoolapi.constants.ApiResponse;
import com.austin.smartschoolapi.constants.CustomMessages;
import com.austin.smartschoolapi.model.auth.OauthClientDetails;
import com.austin.smartschoolapi.model.auth.Role;
import com.austin.smartschoolapi.model.auth.RoleUser;
import com.austin.smartschoolapi.model.auth.User;
import com.austin.smartschoolapi.repository.oauthrepository.OauthClientDetailsRepo;
import com.austin.smartschoolapi.repository.oauthrepository.RoleRepository;
import com.austin.smartschoolapi.repository.oauthrepository.RoleUserRepository;
import com.austin.smartschoolapi.repository.oauthrepository.UserRepository;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OauthService {


    private static final Logger log = LoggerFactory.getLogger(OauthService.class);
    //    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ServletContext context;
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OauthClientDetailsRepo oauthClientDetailsRepo;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleUserRepository roleUserRepository;

    public ResponseEntity authenticatingUser(LoginRequestDto loginRequest) {

        try {
            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String access_token_url = "http://localhost:9090/smart-school-api/oauth/token";

            TokenRequestDto tokenRequestDto = requestAccessToken(loginRequest.getUsername(), loginRequest.getPassword(), access_token_url);

            User principal = (User) authentication.getPrincipal();

            UserDto userDTO = modelMapper.map(principal, UserDto.class);

            //get userID from database

            Optional<User> userIdToGet = findUserByUsername(principal.getUsername());


            userDTO.setId(userIdToGet.get().getId());
            userDTO.setToken(tokenRequestDto.getAccess_token());

            return ResponseEntity.ok().body(new ApiResponse<>(CustomMessages.Success,"User Authenticated Successfully!", userDTO));
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok().body(new ApiResponse<>(CustomMessages.Failed, "Invalid Username or Password"));
        }

    }

    public ResponseEntity createUser(User user) {
        Optional<User> findUser = userRepository.findByUsername(user.getUsername());
        if (findUser.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, "Username: " + user.getUsername()) + " Already Exist");
        }
        if (user.getUserType().equalsIgnoreCase("Admin")) {

            String encodedPassword = passwordEncoder.encode(user.getPassword());

            user.setPlainPassword(user.getPassword());
            user.setPassword(encodedPassword);
            user.setAccountNonExpired(true);
            user.setEnabled(true);
            user.setDateCreated(new Date());
            user.setCredentialsNonExpired(true);
            user.setAccountNonLocked(true);

            OauthClientDetails clientDetails = new OauthClientDetails();
            clientDetails.setAccessTokenValidity(10800);
            clientDetails.setAutoapprove("");
            clientDetails.setAdditionalInformation("{}");
            clientDetails.setAuthorizedGrantTypes("authorization_code,password,refresh_token,implicit");
            clientDetails.setClientId(user.getUsername());
            clientDetails.setClientSecret(encodedPassword);
            clientDetails.setRefreshTokenValidity(10800);
            clientDetails.setResourceIds("smart-school-rest-api");
            clientDetails.setScope("READ,WRITE");
            clientDetails.setWebServerRedirectUri("http://localhost:9090/");

            userRepository.save(user);
            oauthClientDetailsRepo.save(clientDetails);

            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, "User Successfully Created!"));
        }

        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Failed, "Error Saving user with username: " + user.getUsername()));

    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity getUserById(Long id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, findUser.get()));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFoundMessage, "User Not Found!"));
    }

    public ResponseEntity editUser(User user, Long id) {

        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            findUser.get().setAddress(user.getAddress());
            findUser.get().setDob(user.getDob());
            findUser.get().setEmail(user.getEmail());
            findUser.get().setFirstName(user.getFirstName());
            findUser.get().setGender(user.getGender());
            findUser.get().setLastName(user.getLastName());
            findUser.get().setPhone(user.getPhone());
            userRepository.save(findUser.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, "User Updated Successfully!"));

        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFoundMessage, "User Not Found!"));
    }

    public ResponseEntity deleteUser(Long id) {
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.DeletedMessage, "User Has been deleted!"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFoundMessage, "User Not Found!"));
    }

    public ResponseEntity updateUser(Long userId, String newPassword) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent() && optionalUser.get().getId() == userId) {
            optionalUser.get().setEnabled(true);
            optionalUser.get().setAccountNonLocked(true);
            optionalUser.get().setAccountNonExpired(true);
            optionalUser.get().setCredentialsNonExpired(true);
            optionalUser.get().setPlainPassword(newPassword);
            String encodedPassword = passwordEncoder.encode(newPassword);
            optionalUser.get().setPassword(encodedPassword);
            userRepository.save(optionalUser.get());

            Optional<OauthClientDetails> clientDetails = oauthClientDetailsRepo.findByClientId(optionalUser.get().getUsername());
//
            clientDetails.get().setClientSecret(encodedPassword);


            clientDetails.get().setAccessTokenValidity(10800);
            clientDetails.get().setAutoapprove("");
            clientDetails.get().setAdditionalInformation("{}");
            clientDetails.get().setAuthorizedGrantTypes("authorization_code,password,refresh_token,implicit");
            clientDetails.get().setClientId(optionalUser.get().getUsername());
            clientDetails.get().setRefreshTokenValidity(10800);
            clientDetails.get().setResourceIds("smart-school-api");
            clientDetails.get().setScope("READ,WRITE");
            clientDetails.get().setWebServerRedirectUri("http://localhost:9090/");

            oauthClientDetailsRepo.save(clientDetails.get());

            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFoundMessage, "User Not Found!"));
    }

//    -----------------------------------------ROLE SERVICE---------------------------------------------

    public ResponseEntity addRole(Role role) {
        Optional<Role> optionalRole = roleRepository.findRoleByName(role.getName());
        if (optionalRole.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.AlreadyExist, role.getName() + " Already Exist!"));
        }
        roleRepository.save(role);
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, role.getName() + " Role Added Successfully!"));
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public ResponseEntity getRoleById(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, optionalRole.get()));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFoundMessage, "Role Not Found!"));
    }

    public ResponseEntity editRole(Role role, Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            optionalRole.get().setName(role.getName());
            roleRepository.save(optionalRole.get());
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.Success, "Role is Been Updated!"));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFoundMessage, "Role Not Found!"));
    }

    public ResponseEntity deleteRole(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            roleRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>(CustomMessages.DeletedMessage));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFoundMessage, "Role Not Found!"));
    }

    public ResponseEntity addRoleToUser(Long userId, Long roleId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Role> optionalRole = roleRepository.findById(roleId);

        if (optionalRole.isPresent() && optionalUser.isPresent()) {
            RoleUser ru = new RoleUser();
            ru.setUserId(optionalUser.get());
            ru.setRoleId(optionalRole.get());
            RoleUser saveRoleUser = roleUserRepository.save(ru);

            return ResponseEntity.ok().body(new ApiResponse<>(CustomMessages.Success, saveRoleUser.getRoleId().getName() + " Role has been added to " + saveRoleUser.getUserId().getUsername()));
        }
        return ResponseEntity.ok(new ApiResponse<>(CustomMessages.NotFoundMessage, "User or Role not Valid!"));

    }

    public ResponseEntity deleteAssignedRole(Long userId, Long roleId) {
        Optional<RoleUser> optionalRoleUser = roleUserRepository.findByUserIdAndRoleId(userId, roleId);

        if (optionalRoleUser.isPresent()) {
            roleUserRepository.delete(optionalRoleUser.get());
            return ResponseEntity.ok().body(new ApiResponse<>(CustomMessages.Success, CustomMessages.DeletedMessage));
        }
        return ResponseEntity.ok().body(new ApiResponse<>(CustomMessages.NotFound, CustomMessages.NotFoundMessage));
    }


    public TokenRequestDto requestAccessToken(String username, String password, String oauthurl) {

        String access_token = null;
        String refresh_token = null;
        String token_type = null;
        Integer expires_in = null;
        String scope = null;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(username, password);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(oauthurl)
                .queryParam("grant_type", "password")
                .queryParam("username", username)
                .queryParam("password", password);

        URI myUri = builder.buildAndExpand().toUri();


        ResponseEntity<?> result = restTemplate.exchange(myUri, HttpMethod.POST, entity, String.class);


        JSONObject jsonObject = new JSONObject(result.getBody().toString());

        //get access_token from jsonObject here
        access_token = jsonObject.getString("access_token");
        refresh_token = jsonObject.getString("refresh_token");
        token_type = jsonObject.getString("token_type");
        expires_in = jsonObject.getInt("expires_in");
        scope = jsonObject.getString("scope");


        //Passing the variables to the TokenRequestDto so that it can be returned.

        TokenRequestDto dto = new TokenRequestDto();
        dto.setAccess_token(access_token);
        dto.setExpires_in(expires_in);
        dto.setRefresh_token(refresh_token);
        dto.setScope(scope);
        dto.setToken_type(token_type);

        return dto;

    }

    public Optional<User> findUserByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
