package com.bkhech.boot.sample.mvc.dto;

import com.bkhech.boot.sample.mvc.validator.CompositeObj;
import com.bkhech.boot.sample.mvc.validator.Phone;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * Description: ParametersValidationDto
 * 详见：https://docs.jboss.org/hibernate/validator/4.2/reference/zh-CN/html/validator-gettingstarted.html
 * @author guowm 2018/9/25
 */
public class ParametersValidationDto {

    private String version;//版本

    //只能用在字符上
    @NotBlank(message = "指标类型不能为空")
    private String type;//指标类型

    @NotEmpty(message = "list 不能为空")
    private List list;

    @NotNull(message = "时间间隔不能为空")
    private Integer minute; //时间间隔

    //用在字符串上时，可用org.hibernate.validator.constraints.Length替代
    @NotBlank
    @Size(min = 5, max = 10, message = "size 的长度在5-10之间")
    private String size;

    @Negative(message = "negative 不是负数")
    private int negative;
    @PositiveOrZero(message = "positiveOrZero 不是正数或者0")
    private Integer positiveOrZero;

    @AssertFalse(message = "assertFalseOrTrue must be false")
    private Boolean assertFalseOrTrue;

    //注解可写多个，都能有效
    @NotNull
    @DecimalMin(value = "1", message = "decimalMaxOrMin 最小值1")
    @DecimalMax(value = "10", message = "decimalMaxOrMin 最大值10")
    private Integer decimalMaxOrMin;

    //小数，控制精度或者位数时可用
    @NotNull
    @Digits(integer = 2, fraction = 2, message = "digits 限制整数部分最多两位，小数部分最多两位的数值")
    private Double digits;

//    @Email(regexp= "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "email格式错误")
//    private String email;

    //可替代@Email
    @Pattern(regexp= "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "email格式错误")
    private String email;

    @NotNull
    @Future(message = "future 需要比当前时间晚")
    private Date future;
    @NotNull
    @FutureOrPresent(message = "futureOrPresent 需要比当前时间晚或者是当前时间")
    private Date futureOrPresent;
    @NotNull
    @Past(message = "past 需要比当前时间早")
    private Date past;

    @Phone
    @NotBlank
    private String phone;

    @CompositeObj
    @NotNull(message = "compositeParam can not null")
    private CompositeParam compositeParam;

    /** 在controller层实体前，必须加入 @Valid才有效 */
    @Valid
    @NotEmpty
    private List<CompositeParam2> CompositeParam2s;

    public List<CompositeParam2> getCompositeParam2s() {
        return CompositeParam2s;
    }

    public void setCompositeParam2s(List<CompositeParam2> compositeParam2s) {
        CompositeParam2s = compositeParam2s;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }

    public Integer getPositiveOrZero() {
        return positiveOrZero;
    }

    public void setPositiveOrZero(Integer positiveOrZero) {
        this.positiveOrZero = positiveOrZero;
    }

    public Boolean getAssertFalseOrTrue() {
        return assertFalseOrTrue;
    }

    public void setAssertFalseOrTrue(Boolean assertFalseOrTrue) {
        this.assertFalseOrTrue = assertFalseOrTrue;
    }

    public Integer getDecimalMaxOrMin() {
        return decimalMaxOrMin;
    }

    public void setDecimalMaxOrMin(Integer decimalMaxOrMin) {
        this.decimalMaxOrMin = decimalMaxOrMin;
    }

    public Double getDigits() {
        return digits;
    }

    public void setDigits(Double digits) {
        this.digits = digits;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFuture() {
        return future;
    }

    public void setFuture(Date future) {
        this.future = future;
    }

    public Date getFutureOrPresent() {
        return futureOrPresent;
    }

    public void setFutureOrPresent(Date futureOrPresent) {
        this.futureOrPresent = futureOrPresent;
    }

    public Date getPast() {
        return past;
    }

    public void setPast(Date past) {
        this.past = past;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CompositeParam getCompositeParam() {
        return compositeParam;
    }

    public void setCompositeParam(CompositeParam compositeParam) {
        this.compositeParam = compositeParam;
    }
}
