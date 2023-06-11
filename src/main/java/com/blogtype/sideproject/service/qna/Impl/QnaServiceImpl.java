package com.blogtype.sideproject.service.qna.Impl;

import com.blogtype.sideproject.dto.qna.QnaRequestDto;
import com.blogtype.sideproject.dto.qna.QnaResponseDto;
import com.blogtype.sideproject.model.qna.Qna;
import com.blogtype.sideproject.repository.qna.QnaRepository;
import com.blogtype.sideproject.service.qna.QnaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

    private final QnaRepository qnaRepository;

    private static final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @Override
    @Transactional(readOnly = true)
    public List<QnaResponseDto.ResponseQna> findAllQnaList(Long userId) throws Exception {
        List<QnaResponseDto.ResponseQna> resultList = new ArrayList<>();
        try{
            Optional<List<Qna>> optionalQnaList = qnaRepository.findAllQnaList();
            if (optionalQnaList.isPresent()){
                List<Qna> findAllQnaList = optionalQnaList.get();
                resultList = new QnaResponseDto.ResponseQna().qnaConvertToDtoList(findAllQnaList);

            }

        }catch (Exception e){
            log.error("[QnaService] findAllQnaList :: " , e);
            throw new Exception();
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public QnaResponseDto.ResponseQna findQna(Long userId, Long qnaId) throws Exception {
        QnaResponseDto.ResponseQna result = new QnaResponseDto.ResponseQna();
        try{
            Optional<Qna> optionalQna = qnaRepository.findQna(userId,qnaId);
            if (optionalQna.isPresent()){
                Qna findQna = optionalQna.get();
                result = new QnaResponseDto.ResponseQna().qnaConvertToDto(findQna);
            }

        }catch (Exception e){
            log.error("[QnaService] findQna :: " , e);
            throw new Exception();
        }
        return result;
    }

    @Override
    @Transactional
    public List<QnaResponseDto.ResponseQna> findLatestQnaList(Long userId) throws Exception {
        List<QnaResponseDto.ResponseQna> resultList = new ArrayList<>();
        try{
            Optional<List<Qna>> optionalLatestQnaList = qnaRepository.findLatestQnaList(userId);
            if (optionalLatestQnaList.isPresent()){
                List<Qna> findLatestQnaList = optionalLatestQnaList.get();
                resultList = new QnaResponseDto.ResponseQna().qnaConvertToDtoList(findLatestQnaList);

            }

        }catch (Exception e){
            log.error("[QnaService] findLatestQnaList :: " , e);
            throw new Exception();
        }

        return resultList;
    }

    @Override
    @Transactional
    public void createQna(Long userId, QnaRequestDto.RequestQna requestDto) throws Exception{
        try{
            Qna qna = Qna.createQna(userId,requestDto);
            qnaRepository.save(qna);

        }catch (Exception e){
            log.error("[QnaService] createQna :: " , e);
            throw new Exception();
        }

    }

    @Override
    @Transactional
    public void modifyQna(Long userId, Long qnaId, QnaRequestDto.ModifyQna requestDto) throws Exception {
        try{
            Optional<Qna> optionalQna = qnaRepository.findQna(userId,qnaId);
            if (optionalQna.isPresent()){
                Qna findQna = optionalQna.get();
                findQna.updateQna(requestDto);
            }

        }catch (Exception e){
            log.error("[QnaService] modifyQna :: " , e);
            throw new Exception();
        }
    }

    @Override
    @Transactional
    public void deleteQna(Long userId, Long qnaId) throws Exception {
        try{
            Optional<Qna> optionalQna = qnaRepository.findQna(userId,qnaId);
            if (optionalQna.isPresent()){
                Qna findQna = optionalQna.get();
                qnaRepository.delete(findQna);
            }

        }catch (Exception e){
            log.error("[QnaService] deleteQna :: " , e);
            throw new Exception();
        }
    }
}
