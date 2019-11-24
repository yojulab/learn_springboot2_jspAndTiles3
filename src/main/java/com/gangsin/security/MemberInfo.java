package com.gangsin.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberInfo implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String memberSeq; // Member Seq
	private String memberID; // Member ID
	private String memberName; // Member Name
	private String password; // Password
	private String organizationSeq; // 
	private String grandCourseSeq; // grand course seq
	private Set<GrantedAuthority> authorities; // Roles by Member
	private Set<String> grantedMenuHead; // GrantedMenuHead 
	
	public Set<String> getGrantedMenuHead() {
		return grantedMenuHead;
	}
	public void setGrantedMenuHead(Set<String> grantedMenuHead) {
		this.grantedMenuHead = grantedMenuHead;
	}
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public void addAuthority(GrantedAuthority authority) {
		this.authorities.add(authority);
	}
	public MemberInfo(Map<String, String> resultMember, Set<GrantedAuthority> authorities, Set<String> grantedMenuHead) {
		this.memberSeq = (String) resultMember.get("MEMBER_SEQ");
		this.memberID = (String) resultMember.get("MEMBER_ID");
		this.memberName = (String) resultMember.get("NAME");
//		this.password = (String) resultMember.get("PASSWORD");
		this.password = (String) resultMember.get("CRYPT_PASSWORD");
		this.authorities = authorities;
		this.grantedMenuHead = grantedMenuHead;
	}
	public MemberInfo(Map<String, String> resultMember, Set<GrantedAuthority> authorities) {
		this.memberSeq = (String) resultMember.get("MEMBER_SEQ");
		this.memberID = (String) resultMember.get("MEMBER_ID");
		this.memberName = (String) resultMember.get("NAME");
//		this.password = (String) resultMember.get("PASSWORD");
		this.password = (String) resultMember.get("CRYPT_PASSWORD");
		this.organizationSeq = resultMember.get("ORGANIZATION_SEQ");
		this.authorities = authorities;
	}
	public MemberInfo(String memberSeq, String memberID, String email, String MemberName,
			String password, Collection<? extends GrantedAuthority> authorities) {
		this.memberSeq = memberSeq;
		this.memberID = memberID;
		this.memberName = MemberName;
		this.password = password;
		this.authorities = (Set<GrantedAuthority>) authorities;
	}

	public MemberInfo(String memberSeq, String memberID, String email, String MemberName,
			String password, Set<GrantedAuthority> authorities) {
		this.memberSeq = memberSeq;
		this.memberID = memberID;
		this.memberName = MemberName;
		this.password = password;
		this.authorities = authorities;
	}

	public String getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(String memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrganizationSeq() {
		return organizationSeq;
	}
	public void setOrganizationSeq(String organizationSeq) {
		this.organizationSeq = organizationSeq;
	}
	public String getGrandCourseSeq() {
		return grandCourseSeq;
	}
	public void setGrandCourseSeq(String grandCourseSeq) {
		this.grandCourseSeq = grandCourseSeq;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getUsername() {
		return memberID;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}