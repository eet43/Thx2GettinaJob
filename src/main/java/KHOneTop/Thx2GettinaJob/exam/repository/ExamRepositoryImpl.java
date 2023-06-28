package KHOneTop.Thx2GettinaJob.exam.repository;

import KHOneTop.Thx2GettinaJob.calendar.dto.GetCalenderDetailRequest;
import KHOneTop.Thx2GettinaJob.exam.entity.Exam;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
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
    public List<Exam> findExamWithoutBoolean(LocalDate startDate, LocalDate endDate, List<Long> examIds) {
        JPAQuery<Exam> query = queryFactory.selectFrom(exam)
                .innerJoin(exam.examTimeStamp, examTimeStamp)
                .fetchJoin()
                .where(exam.id.in(examIds));

        Predicate expression = buildDateBooleanExpression(startDate, endDate);
        query.where(expression);

        return query.fetch();
    }

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

    private Predicate buildDateBooleanExpression(LocalDate startDate, LocalDate endDate) {
        BooleanBuilder dateBuilder = new BooleanBuilder();
        if(startDate != null && endDate != null) {
            regDatePredicate(dateBuilder, startDate, endDate);
            addRegDatePredicate(dateBuilder, startDate, endDate);
            examDatePredicate(dateBuilder, startDate, endDate);
            resultDatePredicate(dateBuilder, startDate, endDate);
        }
        return dateBuilder;
    }

    private Predicate buildBooleanExpression(LocalDate startDate, LocalDate endDate, GetCalenderDetailRequest request) {
        BooleanBuilder dateBuilder = new BooleanBuilder();
        if (startDate != null && endDate != null) {
            if (Boolean.TRUE.equals(request.HavRegDate())) {
                regDatePredicate(dateBuilder, startDate, endDate);
            }
            if (Boolean.TRUE.equals(request.HavAddRegDate())) {
                addRegDatePredicate(dateBuilder, startDate, endDate);
            }
            if (Boolean.TRUE.equals(request.HavExamDate())) {
                examDatePredicate(dateBuilder, startDate, endDate);
            }
            if (Boolean.TRUE.equals(request.HavResultDate())) {
                resultDatePredicate(dateBuilder, startDate, endDate);
            }
        }

        return dateBuilder;
    }

    private void regDatePredicate(BooleanBuilder dateBuilder, LocalDate startDate, LocalDate endDate) {
        dateBuilder.or(examTimeStamp.regStartDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
        dateBuilder.or(examTimeStamp.regEndDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
    }

    private void addRegDatePredicate(BooleanBuilder dateBuilder, LocalDate startDate, LocalDate endDate) {
        dateBuilder.or(examTimeStamp.addRegStartDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
        dateBuilder.or(examTimeStamp.addRegEndDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
    }

    private void examDatePredicate(BooleanBuilder dateBuilder, LocalDate startDate, LocalDate endDate) {
        dateBuilder.or(examTimeStamp.examDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
    }

    private void resultDatePredicate(BooleanBuilder dateBuilder, LocalDate startDate, LocalDate endDate) {
        dateBuilder.or(examTimeStamp.resultDate.between(startDate.atStartOfDay(), endDate.atTime(23, 59, 59)));
    }


}
