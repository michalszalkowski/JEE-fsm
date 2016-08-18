package pl.btbw.module.post;

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

	@Override
	public String toString() {
		return "Post{" +
				"state='" + state + '\'' +
				'}';
	}
}
