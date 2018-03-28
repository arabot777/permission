package com.mmall.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: shanliu
 * Date: 2018-03-28
 * Time: 23:51
 */
public class BeanValidator {

    //定义全局校验工厂
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    //普通校验方法
    public static <T> Map<String,String> validate(T t , Class... groups){
        Validator validator = validatorFactory.getValidator();
        //自动获取一个校验结果
        Set validateResult = validator.validate(t,groups);
        if (validateResult.isEmpty()){
            return Collections.emptyMap();
        }else {
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext()){
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                errors.put(violation.getPropertyPath().toString(),violation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String,String> validateList(Collection<?> collection){
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()){
                return  Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object,new Class[0]);
        }while (errors.isEmpty());
        return errors;
    }

    //任何校验使用这一个方法就可
    public static Map<String,String> valideObject(Object first,Object...objects){
        if (objects!=null&&objects.length>0){
           return validateList(Lists.asList(first,objects));
        }else{
           return validate(first,new Class[0]);
        }
    }
}
