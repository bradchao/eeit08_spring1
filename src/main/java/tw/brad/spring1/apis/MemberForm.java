package tw.brad.spring1.apis;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MemberForm {
	private String account;
	private List<MultipartFile> files;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
	
}
