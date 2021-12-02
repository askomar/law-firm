package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.*;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.persistence.PaperworkRepository;
import com.solvd.lawfirm.persistence.impl.mapper.PaperworkMapperImpl;
import com.solvd.lawfirm.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PaperworkServiceImpl implements PaperworkService {

    private static final Logger logger = LogManager.getLogger(PaperworkServiceImpl.class);

    private static final PaperworkRepository PAPERWORK_REPOSITORY = new PaperworkMapperImpl();
    private static final PaperworkTypeService PAPERWORK_TYPE_SERVICE = PaperworkTypeServiceImpl.getInstance();
    private static final ClientFolderService CLIENT_FOLDER_SERVICE = ClientFolderServiceImpl.getInstance();
    private static final CourtService COURT_SERVICE = CourtServiceImpl.getInstance();
    private static final JudgeService JUDGE_SERVICE = JudgeServiceImpl.getInstance();

    private static PaperworkService instance;

    private PaperworkServiceImpl() {
    }

    public static PaperworkService getInstance() {
        if (instance == null) {
            instance = new PaperworkServiceImpl();
        }
        return instance;
    }

    @Override
    public void create(Paperwork paperwork, Long paperworkTypeId, Long folderId) throws ParameterIsEmpty, ResourceNotFoundException {
        create(paperwork, paperworkTypeId, folderId, null, null);
    }

    @Override
    public void create(Paperwork paperwork, Long paperworkTypeId, Long folderId, Long courtId, Long judgeId) throws ParameterIsEmpty, ResourceNotFoundException {
        if (!isValid(paperwork)) {
            throw new ParameterIsEmpty("Paperwork's one or more parameters are not specified");
        }
        PaperWorkType paperWorkType = PAPERWORK_TYPE_SERVICE.findById(paperworkTypeId);
        ClientFolder clientFolder = CLIENT_FOLDER_SERVICE.findById(folderId);
        Court court = null;
        Judge judge = null;
        if (courtId != null && judgeId != null) {
            court = COURT_SERVICE.findById(courtId);
            judge = JUDGE_SERVICE.findById(judgeId);
        }
        paperwork.setType(paperWorkType);
        paperwork.setFolder(clientFolder);
        paperwork.setCourt(court);
        paperwork.setJudge(judge);
        PAPERWORK_REPOSITORY.create(paperwork);
    }

    @Override
    public List<Paperwork> findAll() throws ResourceNotFoundException {
        List<Paperwork> paperworks = PAPERWORK_REPOSITORY.findAll();
        if (paperworks.size() <= 0) {
            throw new ResourceNotFoundException("Paperworks was not found");
        }
        paperworks.forEach(paperwork -> {
            PaperWorkType paperWorkType = null;
            try {
                paperWorkType = PAPERWORK_TYPE_SERVICE.findById(paperwork.getType().getId());

                ClientFolder clientFolder = CLIENT_FOLDER_SERVICE.findById(paperwork.getFolder().getId());
                Court court = null;
                Judge judge = null;
                if (paperwork.getCourt() != null && paperwork.getJudge() != null) {
                    court = COURT_SERVICE.findById(paperwork.getCourt().getId());
                    judge = JUDGE_SERVICE.findById(paperwork.getJudge().getId());
                }

                paperwork.setType(paperWorkType);
                paperwork.setFolder(clientFolder);
                paperwork.setCourt(court);
                paperwork.setJudge(judge);
            } catch (ResourceNotFoundException e) {
                logger.error("Exception when try to initialise findAll query by paperworks");
            }
        });
        return paperworks;
    }

    @Override
    public Paperwork findById(Long id) throws ResourceNotFoundException {
        Paperwork paperwork = PAPERWORK_REPOSITORY.findById(id);
        if (paperwork == null) {
            throw new ResourceNotFoundException("Paperwork with id = " + id + " was not found");
        }
        paperwork.setType(PAPERWORK_TYPE_SERVICE.findById(paperwork.getType().getId()));
        paperwork.setFolder(CLIENT_FOLDER_SERVICE.findById(paperwork.getFolder().getId()));
        if (paperwork.getCourt() != null && paperwork.getJudge() != null) {
            paperwork.setCourt(COURT_SERVICE.findById(paperwork.getCourt().getId()));
            paperwork.setJudge(JUDGE_SERVICE.findById(paperwork.getJudge().getId()));
        }
        return paperwork;
    }

    @Override
    public int update(Paperwork paperwork) throws ParameterIsEmpty, ResourceNotFoundException {
        if (paperwork.getId() == null) {
            throw new ParameterIsEmpty("Can't update paperwork - there is no id");
        }
        if (!isValid(paperwork)) {
            throw new ParameterIsEmpty("Paperwork's one or more parameters are not specified");
        }
        if (!paperwork.getType().equals(PAPERWORK_TYPE_SERVICE.findById(paperwork.getType().getId()))) {
            PAPERWORK_TYPE_SERVICE.update(paperwork.getType());
        }
        if (!paperwork.getFolder().equals(CLIENT_FOLDER_SERVICE.findById(paperwork.getFolder().getId()))) {
            CLIENT_FOLDER_SERVICE.update(paperwork.getFolder());
        }
        if (paperwork.getCourt() != null && paperwork.getJudge() != null) {
            if (!paperwork.getCourt().equals(COURT_SERVICE.findById(paperwork.getCourt().getId()))) {
                COURT_SERVICE.update(paperwork.getCourt());
            }
            if (!paperwork.getJudge().equals(JUDGE_SERVICE.findById(paperwork.getJudge().getId()))) {
                JUDGE_SERVICE.update(paperwork.getJudge());
            }
        }
        return PAPERWORK_REPOSITORY.update(paperwork);
    }

    @Override
    public int delete(Paperwork paperwork) throws ParameterIsEmpty {
        if (paperwork.getId() == null) {
            throw new ParameterIsEmpty("Can't delete paperwork - there is no id");
        }
        return PAPERWORK_REPOSITORY.delete(paperwork);
    }

    private boolean isValid(Paperwork paperwork) {
        return paperwork.getFolder() != null && paperwork.getUrl() != null && paperwork.getTitle() != null && paperwork.getType() != null;
    }
}
