package pl.btbw.module;

import org.statefulj.persistence.annotations.State;

public class Post {

	@State
	private String state;

	public Post(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}
