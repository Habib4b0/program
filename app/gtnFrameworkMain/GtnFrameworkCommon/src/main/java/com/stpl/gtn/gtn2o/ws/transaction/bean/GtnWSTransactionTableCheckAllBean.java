package com.stpl.gtn.gtn2o.ws.transaction.bean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GtnWSTransactionTableCheckAllBean {

	private boolean checkAll = false;

	private Set<Integer> checkedIdSet = new HashSet<>();

	private Set<Integer> unCheckedIdSet = new HashSet<>();

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public Set<Integer> getCheckedIdSet() {
		return checkedIdSet != null ? Collections.unmodifiableSet(checkedIdSet) : checkedIdSet;
	}

	public void setCheckedIdSet(Set<Integer> checkedIdSet) {
		this.checkedIdSet = checkedIdSet != null ? new HashSet<>(checkedIdSet) : checkedIdSet;
	}

	public void addtoCheckedIdSet(Integer value) {
		checkedIdSet.add(value);
	}

	public void removeFromCheckedIdSet(Integer value) {
		checkedIdSet.remove(value);
	}

	public Set<Integer> getUnCheckedIdSet() {
		return unCheckedIdSet != null ? Collections.unmodifiableSet(unCheckedIdSet) : unCheckedIdSet;
	}

	public void setUnCheckedIdSet(Set<Integer> unCheckedIdSet) {
		this.unCheckedIdSet = unCheckedIdSet != null ? new HashSet<>(unCheckedIdSet) : unCheckedIdSet;
	}

	public void addtoUnCheckedIdSet(Integer uncheckedId) {
		unCheckedIdSet.add(uncheckedId);
	}

	public boolean removeFromUnCheckedIdSet(Integer uncheckedId) {
		return unCheckedIdSet.remove(uncheckedId);
	}

}
