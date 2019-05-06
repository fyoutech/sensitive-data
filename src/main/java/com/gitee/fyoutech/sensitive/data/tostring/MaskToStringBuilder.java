package com.gitee.fyoutech.sensitive.data.tostring;

import com.gitee.fyoutech.sensitive.data.anno.Mask;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/**
 * @author ipanocloud
 * @since 2019-05-06 11:16
 */
public class MaskToStringBuilder extends ReflectionToStringBuilder {
    public MaskToStringBuilder(Object object) {
        super(object);
    }

    public MaskToStringBuilder(Object object, ToStringStyle style) {
        super(object, style);
    }

    protected void appendFieldsIn(Class clazz) {
        if (clazz.isArray()) {
            this.reflectionAppendArray(this.getObject());
        } else {
            Field[] fields = clazz.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);

            for(int i = 0; i < fields.length; ++i) {
                Field field = fields[i];
                String fieldName = field.getName();
                if (this.accept(field)) {
                    try {
                        Object fieldValue = this.getValue(field);
                        if (fieldValue != null) {
                            Mask anno = (Mask)field.getAnnotation(Mask.class);
                            if (anno != null && field.getType() == String.class) {
                                String strFieldVal = (String)fieldValue;
                                int length = strFieldVal.length();
                                int totalNoMaskLen = anno.prefixNoMaskLen() + anno.suffixNoMaskLen();
                                if (totalNoMaskLen == 0) {
                                    fieldValue = this.fillMask(anno.maskStr(), length);
                                }

                                if (totalNoMaskLen < length) {
                                    StringBuilder sb = new StringBuilder();

                                    for(int j = 0; j < strFieldVal.length(); ++j) {
                                        if (j < anno.prefixNoMaskLen()) {
                                            sb.append(strFieldVal.charAt(j));
                                        } else if (j > strFieldVal.length() - anno.suffixNoMaskLen() - 1) {
                                            sb.append(strFieldVal.charAt(j));
                                        } else {
                                            sb.append(anno.maskStr());
                                        }
                                    }

                                    fieldValue = sb.toString();
                                }
                            }

                            this.append(fieldName, fieldValue);
                        }
                    } catch (IllegalAccessException var13) {
                        throw new InternalError("Unexpected IllegalAccessException: " + var13.getMessage());
                    }
                }
            }

        }
    }

    private String fillMask(String maskStr, int length) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            sb.append(maskStr);
        }

        return sb.toString();
    }
}
