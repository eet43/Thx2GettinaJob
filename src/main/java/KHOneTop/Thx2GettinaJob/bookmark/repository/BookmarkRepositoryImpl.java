package KHOneTop.Thx2GettinaJob.bookmark.repository;

import KHOneTop.Thx2GettinaJob.bookmark.dto.BookmarkInfo;
import KHOneTop.Thx2GettinaJob.bookmark.entity.QBookmark;
import KHOneTop.Thx2GettinaJob.exam.entity.QExam;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static KHOneTop.Thx2GettinaJob.bookmark.entity.QBookmark.bookmark;
import static KHOneTop.Thx2GettinaJob.exam.entity.QExam.*;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom{

    private final JPAQueryFactory queryFactory;

}
