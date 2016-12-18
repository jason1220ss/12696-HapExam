package hbi.core.HapProject.service;


import com.hand.hap.core.IRequest;
import hbi.core.HapProject.dto.ExportsConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by jason on 2016/12/18.
 */
public interface IExportsService {
    void exporstExcel(String var1, ExportsConfig var2, IRequest var3, OutputStream var4) throws IOException;

    void exportsAndDownloadExcel(String var1, ExportsConfig var2, HttpServletRequest var3, HttpServletResponse var4, IRequest var5) throws IOException;
}