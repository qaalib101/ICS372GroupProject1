package groupProject1.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import groupProject1.entities.Member;

public class MemberList implements Iterable<Member>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<Member> members = new LinkedList<Member>();
	private static MemberList memberList;

	private MemberList() {

	}

	public static MemberList getInstance() {
		if (memberList == null) {
			memberList = new MemberList();
		}
		return memberList;
	}

	public Member search(String memberId) {
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			if (member.getId().equals(memberId)) {
				return member;
			}
		}
		return null;
	}

	public Member searchByName(String memberName) {
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			if (member.getName().equals(memberName)) {
				return member;
			}
		}
		return null;
	}

	/**
	 * Inserts a member into the collection
	 * 
	 * @param member the member to be inserted
	 * @return true iff the member could be inserted. Currently always true
	 */
	public boolean insertMember(Member member) {
		members.add(member);
		return true;
	}

	/**
	 * Removes a member from the catalog
	 * 
	 * @param memberID member id
	 * @return true if the member could be removed
	 */
	public boolean removeMember(String memberId) {
		Member member = search(memberId);
		if (member == null) {
			return false;
		} else {
			return members.remove(member);
		}
	}

	public Iterator<Member> iterator() {
		return members.iterator();
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return members.toString();
	}

}
