package am.basic.jdbcStart.util.Constants;


import com.sun.scenario.effect.impl.sw.java.JSWPerspectiveTransformPeer;

public interface Message {
    String DUPLICATE_USER_MESSAGE = "this username already exists please try again";
    String CODE_SUCCESSFULLY_SEND_MESSAGE = "your code is send to your email account please verify ";
    String INTERNAL_PROBLEM_MESSAGE = "oops , something went wrong";
    String REGISTER_PROBLEM_MESSAGE = "if you're not registered please register";
    String INTERNAL_FORGET_MESSAGE = "make sure your password or username are right";
    String INVALID_USERNAME_MESSAGE = "username must be more than 5 characters";
    String INVALID_CODE_MESSAGE = "code must be more than 5 characters";
    String NOT_MATCHING_CODE_MESSAGE = "codes don't match";
    String INVALID_PASSWORD_MESSAGE = "password must have at least 8 characters, has  at least 1 number,upper,lower letter and punctuation";
    String SUCCESSFUL_REGISTER_MESSAGE = "congrats you've been registered successfully,now please log in";
    String NOT_MATCHING_Passwords_MESSAGE = "they don't match(((";
    String SUCCESSFUL_PASSWORD_CHANGING = "your password is changed , please log in";
    String STATUS_NOT_MATCHING_MESSAGE = "status is set 0 you need to switch it to 1";
    String INVALID_CREDENTIALS_MESSAGE = "Wrong username or password";
    String VERIFICATION_SUCCESS_MESSAGE = "verification is okay,please log in";
    String USER_NOT_EXIST_MESSAGE = "There is some problem with your account";
    String SESSION_EXPIRED_MESSAGE = "Your session expired please log in again";
    String UNVERIFIED_MESSAGE = "Your account is not verified,please verify !";
    String SUCCESSFULLY_ADDED_NOTE = "congrats! your note is added";
    String NOTE_IS_NOT_ADDED_MESSAGE = "you haven't added any note ,please try again";
    String NOTE_DOES_NOT_EXIST_MESSAGE = "there is no note";
    String NOTES_HAS_SUCCESSFULLY_BEEN_ERASED="your all notes are deleted";
}
