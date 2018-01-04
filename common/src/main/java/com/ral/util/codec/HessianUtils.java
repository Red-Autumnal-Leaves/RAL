package com.ral.util.codec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

/**
 * 
 * @author victor
 * @desc 序列化工具类
 */
public final class HessianUtils {
	
	private static Logger logger = Logger.getLogger(HessianUtils.class);
	
	private HessianUtils(){
	}
	
	 /**
     * 编码
     *
     * @param obj 待序列化对象
     * @return 字节
     */
    public static byte[] encode(Object obj) {
        ByteArrayOutputStream byteArray = null;
        Hessian2Output output = null;
        try {
            byteArray = new ByteArrayOutputStream();
            output = new Hessian2Output(byteArray);

            output.writeObject(obj);
            output.flush();
            return byteArray.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (byteArray != null) {
                try {
                    byteArray.close();
                } catch (IOException e) {
                	logger.error("Close input error.", e);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                	logger.error("Close output error.", e);
                }
            }
        }
    }

    /**
     * 解码
     *
     * @param data 字节
     * @return 反序列化对象
     */
    public static Object decode(byte[] data) {
        Hessian2Input input = null;
        try {
            input = new Hessian2Input(new ByteArrayInputStream(data));
            return input.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                	logger.error("Close output error.", e);
                }
            }
        }
    }

}
