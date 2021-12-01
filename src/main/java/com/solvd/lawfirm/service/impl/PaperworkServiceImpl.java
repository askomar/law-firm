package com.solvd.lawfirm.service.impl;

import com.solvd.lawfirm.domain.Paperwork;
import com.solvd.lawfirm.persistence.PaperworkRepository;
import com.solvd.lawfirm.persistence.impl.PaperworkRepositoryImpl;
import com.solvd.lawfirm.service.*;

import java.util.List;

public class PaperworkServiceImpl implements PaperworkService {

    private static final PaperworkRepository PAPERWORK_REPOSITORY = PaperworkRepositoryImpl.getInstance();
    private static final PaperworkTypeService PAPERWORK_TYPE_SERVICE = new PaperworkTypeServiceImpl();
    private static final ClientFolderService CLIENT_FOLDER_SERVICE = new ClientFolderServiceImpl();
    private static final CourtService COURT_SERVICE = new CourtServiceImpl();
    private static final JudgeService JUDGE_SERVICE = new JudgeServiceImpl();


    @Override
    public void create(Paperwork paperwork) {
        if (paperwork.getType() != null && paperwork.getType().getId() == null) {
            PAPERWORK_TYPE_SERVICE.create(paperwork.getType());
        }
        if (paperwork.getFolder() != null && paperwork.getFolder().getId() == null) {
            CLIENT_FOLDER_SERVICE.create(paperwork.getFolder());
        }
        if (paperwork.getCourt() != null && (paperwork.getCourt().getId() == null)) {
            COURT_SERVICE.create(paperwork.getCourt());
        }
        if (paperwork.getJudge() != null && (paperwork.getJudge().getId() == null)) {
            JUDGE_SERVICE.create(paperwork.getJudge());
        }
        PAPERWORK_REPOSITORY.create(paperwork);
    }

    @Override
    public List<Paperwork> findAll() {
        return PAPERWORK_REPOSITORY.findAll();
    }

    @Override
    public Paperwork findById(Long id) {
        return PAPERWORK_REPOSITORY.findById(id);
    }

    @Override
    public int update(Paperwork paperwork) {
        return PAPERWORK_REPOSITORY.update(paperwork);
    }

    @Override
    public int delete(Paperwork paperwork) {
        return PAPERWORK_REPOSITORY.delete(paperwork);
    }
}
