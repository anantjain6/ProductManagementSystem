package me.anant.PMS.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Password {
	
	@NotNull
	private String oldPassword;
	
	@NotNull
	@Min(value=8, message="New Password can not be less than 8 characters")
	private String newPassword;

	@NotNull
	@Min(value=8, message="New Password can not be less than 8 characters")
	private String confirmPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
