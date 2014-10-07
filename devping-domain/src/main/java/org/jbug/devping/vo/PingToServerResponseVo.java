package org.jbug.devping.vo;

public class PingToServerResponseVo extends DataVo {
	private String channelId;
	private String result;
	
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return "PingToServerResponseVo [channelId=" + channelId + ", result="
				+ result + "]";
	}
}
