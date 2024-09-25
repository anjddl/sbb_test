package com.anjddl.sbb;

import com.anjddl.sbb.Artcle.Article;
import com.anjddl.sbb.Artcle.ArticleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	void testJpa() {
		Article a1 = new Article();
		a1.setSubject("Article이 무엇인가요?");
		a1.setContent("Article에 대해서 알고 싶습니다.");
		a1.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(a1);

	}
	@Transactional
	@Test
	void testJpa2() {
		List<Article> all = this.articleRepository.findAll();
		assertEquals(2, all.size());

		Article q = all.get(0);
		assertEquals("Article이 무엇인가요?", q.getSubject());
	}
	@Test
	void testJpa3() {
		Optional<Article> oq = this.articleRepository.findById(1);
		if(oq.isPresent()) {
			Article q = oq.get();
			assertEquals("Article이 무엇인가요?", q.getSubject());
		}
	}
	@Test
	void testJpa4() {
		Article a = this.articleRepository.findBySubject("Article이 무엇인가요?");
		assertEquals(1, a.getId());
	}
	@Test
	void testJpa5() {
		Article a = this.articleRepository.findBySubjectAndContent(
				"Article이 무엇인가요?", "Article에 대해서 알고 싶습니다.");
		assertEquals(1, a.getId());
	}
	@Test
	void testJpa6() {
		List<Article> aList = this.articleRepository.findBySubjectLike("Article%");
		Article a = aList.get(0);
		assertEquals("Article이 무엇인가요?", a.getSubject());
	}
	@Test
	void testJpa7() {
		Optional<Article> oa = this.articleRepository.findById(1);
		assertTrue(oa.isPresent());
		Article a = oa.get();
		a.setSubject("수정된 제목");
		this.articleRepository.save(a);
	}
}
