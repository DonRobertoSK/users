package sk.skog.users.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements Serializable {
	@Id
	@Column(name = "USER_ID")
	private Integer userId;
	@Column(name = "USER_GUID")
	private String userGuid;
	@Column(name = "USER_NAME")
	private String userName;
	
	public User() {
	}
	
	public User(Integer userId, String userGuid, String userName) {
		this.userId = userId;
		this.userGuid = userGuid;
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return String.format("User - USER_ID: %s, USER_GUID: %s, USER_NAME: %s", userId, userGuid, userName);
	}

}
