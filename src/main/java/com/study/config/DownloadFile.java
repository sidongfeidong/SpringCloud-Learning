package com.study.config;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 主要用于下载导入模板，页面上传入的request中parameter中，filename代表了要下载的模板的名称
 */
public class DownloadFile extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4541729035831587727L;

	private final static String HOME_PATH = DownloadFile.class.getResource("/").getPath();
	private final static String DOWNLOAD_TEMP_FILE = HOME_PATH.subSequence(0, HOME_PATH.indexOf("WEB-INF")) + "file/";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filename = req.getParameter("filename");
		resp.reset();// 清空输出流

		String resultFileName = filename + System.currentTimeMillis() + ".xls";
		resultFileName = URLEncoder.encode(resultFileName, "UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Content-disposition", "attachment; filename=" + resultFileName);// 设定输出文件头
		resp.setContentType("application/msexcel");// 定义输出类型
		try (
				// 输入流：本地文件路径
				DataInputStream in = new DataInputStream(
						new FileInputStream(new File(DOWNLOAD_TEMP_FILE + filename + ".xls")));
				// 输出流
				OutputStream out = resp.getOutputStream();) {
			// 输出文件
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
//			out.close();
//			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			resp.reset();
			try (OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");) {
				String data = "<script language='javascript'>alert(\"\\u64cd\\u4f5c\\u5f02\\u5e38\\uff01\");</script>";
				writer.write(data);
//				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}