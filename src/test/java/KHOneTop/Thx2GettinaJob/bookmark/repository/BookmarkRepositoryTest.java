package KHOneTop.Thx2GettinaJob.bookmark.repository;

import KHOneTop.Thx2GettinaJob.Thx2GettinaJobApplication;
import KHOneTop.Thx2GettinaJob.bookmark.entity.Bookmark;
import KHOneTop.Thx2GettinaJob.bookmark.repository.BookmarkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookmarkRepositoryTest {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Test
    public void testFindByUserId() {
        // given
        Bookmark bookmark1 = Bookmark.create(1L, "토익");
        Bookmark bookmark2 = Bookmark.create(1L, "HSK");
        Bookmark bookmark3 = Bookmark.create(2L, "토익");
        bookmarkRepository.save(bookmark1);
        bookmarkRepository.save(bookmark2);
        bookmarkRepository.save(bookmark3);

        // when
        List<String> findNames = bookmarkRepository.findExamNamesByUserId(1L);

        // then
        assertThat(findNames).hasSize(2);
        assertThat(findNames).contains("토익", "HSK");
    }
}
