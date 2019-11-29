package Manager;

import com.qualitascorpus.testsupport.IO;
import exception.EmptyInputException;
import exception.QuitException;

public class InputManager {

    public InputManager(){ }

    public void verifyInput(String input) throws EmptyInputException, QuitException, NumberFormatException {
        if (input.isEmpty()){
            throw new EmptyInputException();
        }
        else if (input.toLowerCase().equals("q")){
            throw new QuitException();
        }

        Integer.parseInt(input);
    }
}
