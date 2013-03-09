package org.tothought.stackoverflow.result;

import org.codehaus.jackson.annotate.JsonProperty;

public abstract class AbstractResult {

	private Integer quotaRemaining;

	private Integer quotaMax;

	private boolean hasMore;

	private Integer page;
	
	private Boolean backOff;
	
	@JsonProperty("quota_remaining")
	public Integer getQuotaRemaining() {
		return quotaRemaining;
	}

	public void setQuotaRemaining(Integer quotaRemaining) {
		this.quotaRemaining = quotaRemaining;
	}

	@JsonProperty("quota_max")
	public Integer getQuotaMax() {
		return quotaMax;
	}

	public void setQuotaMax(Integer quotaMax) {
		this.quotaMax = quotaMax;
	}

	@JsonProperty("has_more")
	public boolean isHasMore() {
		return hasMore;
	}

	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	
	@JsonProperty("page")
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Boolean getBackOff() {
		return backOff;
	}

	public void setBackOff(Boolean backOff) {
		this.backOff = backOff;
	}
	
}
