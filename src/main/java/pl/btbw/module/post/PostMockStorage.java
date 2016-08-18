package pl.btbw.module.post;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class PostMockStorage {

	private Map<Integer, Post> posts = new HashMap<>();

	@PostConstruct
	private void init() {
		posts.put(1, new Post(PostState.DRAFT.toString()));
		posts.put(2, new Post(PostState.PUBLISHED.toString()));
		posts.put(3, new Post(PostState.PUBLISHED.toString()));
		posts.put(4, new Post(PostState.UNPUBLISHED.toString()));
		posts.put(5, new Post(PostState.UNPUBLISHED.toString()));
		posts.put(6, new Post(PostState.DELETED.toString()));
	}

	public Post getPost(Integer id) {
		return posts.get(id);
	}
}
