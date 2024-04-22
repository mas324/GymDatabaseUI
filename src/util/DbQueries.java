package util;

public interface DbQueries {
	public boolean addMember(Member newMember);

	public Member getMember(String member);

	public boolean addTrainer(String[] newTrainer);

	public String[] getTrainer(String trainer);

	public boolean addClass(String[] newClass);

	public String[] getClass(String className);

	public boolean addMembershipTier(Membership newTier);

	public Membership getMembershipTier(String tier);

}
