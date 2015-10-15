package infrastructure;

import model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Paul on 15.10.2015.
 */
public class UserH2DAOValidator implements ConstraintValidator<H2DAO, User> {

    @Override
    public void initialize(H2DAO h2DAO){

    }
    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext){

        if(user.getId() >0 || user.getPassword() == null || user.getEmail() == null || user.getWorkType() == null){
            return true;
        }
        // int n = user.getId();
        return  true;

    }
}
