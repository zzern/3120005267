package pers.zz.exception;

/**
 * @Author: zz
 * @Date: 2022/09/18 0:41
 * @Description:
 */
public class TooShortException extends Exception{
    private String message;
    public TooShortException(){}
    public TooShortException(String message){
        super(message);
    }

}
