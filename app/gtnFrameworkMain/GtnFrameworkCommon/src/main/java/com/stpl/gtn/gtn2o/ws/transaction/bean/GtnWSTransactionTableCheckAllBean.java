package com.stpl.gtn.gtn2o.ws.transaction.bean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GtnWSTransactionTableCheckAllBean {

	private boolean checkAll = false;

	private Set<String> checkedIdSet = new HashSet<>();

	private Set<String> unCheckedIdSet = new HashSet<>();

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public Set<String> getCheckedIdSet() {
		return checkedIdSet != null ? Collections.unmodifiableSet(checkedIdSet) : checkedIdSet;
	}

	public void setCheckedIdSet(Set<String> checkedIdSet) {
		this.checkedIdSet = checkedIdSet != null ? new HashSet<>(checkedIdSet) : checkedIdSet;
	}

	public void addtoCheckedIdSet(String value) {
		checkedIdSet.add(value);
	}

	public void removeFromCheckedIdSet(String value) {
		checkedIdSet.remove(value);
	}

	public Set<String> getUnCheckedIdSet() {
		return unCheckedIdSet != null ? Collections.unmodifiableSet(unCheckedIdSet) : unCheckedIdSet;
	}

	public void setUnCheckedIdSet(Set<String> unCheckedIdSet) {
		this.unCheckedIdSet = unCheckedIdSet != null ? new HashSet<>(unCheckedIdSet) : unCheckedIdSet;
	}

	public void addtoUnCheckedIdSet(String uncheckedId) {
		unCheckedIdSet.add(uncheckedId);
	}

	public boolean removeFromUnCheckedIdSet(String uncheckedId) {
		return unCheckedIdSet.remove(uncheckedId);
	}

}
