package edu.ics372.groupProject1.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.groupProject1.entities.Member;

/**
 * The collection class for members
 * 
 * @author
 *
 */
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

	/**
	 * Inserts a member into the collection
	 * 
	 * @param member the member to be inserted
	 * @return true if the member could be inserted. Currently always true
	 */
	public boolean insertMember(Member member) {
		members.add(member);
		return true;
	}

	/**
	 * Checks whether a member with a given member id exists.
	 * 
	 * @param memberID the id of the member
	 * @return member object if the member exists and null if not.
	 * 
	 */
	public Member search(String memberID) {
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = (Member) iterator.next();
			if (member.getId().equals(memberID)) {
				return member;
			}
		}
		return null;
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

	/**
	 * The iterator of members
	 * 
	 * @return iterator of member objects
	 */
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
