package com.spping.ath.common.utils;

import com.spping.ath.common.exceptions.AthException;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @Author huabao.fang
 * @Date 11:03 2019-02-24
 * @Description 校验工具类
 **/
public class ValidationUtils {


    private static Validator validator = Validation
            .byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory()
            .getValidator();

    public static <T> void validate(T obj){
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(obj);

        if(constraintViolationSet.size()>0){
            for(ConstraintViolation constraintViolation:constraintViolationSet){
                throw new AthException("999999",constraintViolation.getPropertyPath()
                        +"="+constraintViolation.getInvalidValue()
                        +"校验不通过,原因是 "
                        +constraintViolation.getMessage()
                );
            }
        }
    }

    public static void main(String[] args) {
        ValidBean validBean = new ValidBean();
        validBean.setPayTime("00");
        validBean.setStatus("AA");
        validate(validBean);
    }
}

/**
 * Bean Validation 中内置的 constraint
 * @Null   被注释的元素必须为 null
 * @NotNull    被注释的元素必须不为 null
 * @AssertTrue     被注释的元素必须为 true
 * @AssertFalse    被注释的元素必须为 false
 * @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 * @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past   被注释的元素必须是一个过去的日期
 * @Future     被注释的元素必须是一个将来的日期
 * @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
 * Hibernate Validator 附加的 constraint
 * @NotBlank(message =)   验证字符串非null，且长度必须大于0
 * @Email  被注释的元素必须是电子邮箱地址
 * @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内
 * @NotEmpty   被注释的字符串的必须非空
 * @Range(min=,max=,message=)  被注释的元素必须在合适的范围内
 */
class ValidBean{

    @NotEmpty(message = "不能为空")
    @Size(max=14,message="长度不能超过{max}位")
    private String payTime;

    @Pattern(regexp = "0[0123]", message = "状态只能为00或01或02或03")
    private String status;

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
