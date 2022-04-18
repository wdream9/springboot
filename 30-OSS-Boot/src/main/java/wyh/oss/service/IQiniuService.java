package wyh.oss.service;

import com.qiniu.common.QiniuException;

import java.io.File;
import java.io.InputStream;

public interface IQiniuService {

    /**
     * 以文件的形式上传
     *
     * @param file
     * @param fileName:
     * @return: java.lang.String
     */
    String uploadFile(File file, String fileName) throws QiniuException;
}
