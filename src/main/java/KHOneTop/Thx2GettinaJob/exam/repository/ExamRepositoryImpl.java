package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.bookmark.dto.GetCalenderDetailRequest;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static KHOneTop.Thx2GettinaJob.exam.entity.QExam.*;
import static KHOneTop.Thx2GettinaJob.exam.entity.QExamTimeStamp.*;

@Repository
@RequiredArgsConstructor
public class ExamRepositoryImpl implements ExamRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Exam> findExamByExamTimeStampFields(LocalDate startDate, LocalDate endDate, GetCalenderDetailRequest request) {
        JPAQuery<Exam> query = queryFactory.selectFrom(exam)
                .innerJoin(exam.examTimeStamp, examTimeStamp)
                .fetchJoin()
                .where(exam.id.eq(request.examId()));

        Predicate expression = buildBooleanExpression(startDate, endDate, request);
        query.where(expression);

        return Optional.ofNullable(query.fetchOne());
    }

    private Predicate buildBooleanExpression(LocalDate startDate, LocalDate endDate, GetCalenderDetailRequest request) {
        BooleanBuilder dateBuilder = new BooleanBuilder();
        if (startDate != null && endDate != null) {
            if (Boolean.TRUE.equals(request.HavRegDate())) {
                dateBuilder.or(examTimeStamp.regStartDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
                dateBuilder.or(examTimeStamp.regEndDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
            }
            if (Boolean.TRUE.equals(request.HavAddRegDate())) {
                dateBuilder.or(examTimeStamp.addRegStartDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
                dateBuilder.or(examTimeStamp.addRegEndDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
            }
            if (Boolean.TRUE.equals(request.HavExamDate())) {
                dateBuilder.or(examTimeStamp.examDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
            }
            if (Boolean.TRUE.equals(request.HavResultDate())) {
                dateBuilder.or(examTimeStamp.resultDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
            }
        }

        return dateBuilder;
    }


}
