package com.solvd.lawfirm;

import com.solvd.lawfirm.domain.*;
import com.solvd.lawfirm.domain.exception.ParameterIsEmpty;
import com.solvd.lawfirm.domain.exception.ResourceNotFoundException;
import com.solvd.lawfirm.service.*;
import com.solvd.lawfirm.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(Main.class);

        List<LawOffice> lawOffices = Arrays.asList(
                createLawOffice("Маслов, Гашинский и партнеры", "улица Пушкина, дом 10"),
                createLawOffice("Белорусская республиканская коллегия адвокатов", "улица Коллекторная, дом 10"),
                createLawOffice("Минская городская коллегия адвокатов", "улица Захарова, дом 506"),
                createLawOffice("Специализированная юридическая консультация Белинюрколлегия", "улица Богдановича, дом 70"),
                createLawOffice("Юридическая консультация № 2 Ленинского района", "улица Ульяновская, дом 4")
        );

        List<Lawyer> lawyers = Arrays.asList(
                createLawyer(lawOffices.get(0), "Зубенко", "Михаил", "Петрович", LocalDate.of(2000, 1, 1), LocalDate.of(2008, 10, 10)),
                createLawyer(lawOffices.get(1), "Карзан", "Валентина", "Михайловна", LocalDate.of(1988, 9, 15), LocalDate.of(2007, 2, 4)),
                createLawyer(lawOffices.get(2), "Король", "Юрий", "Викторович", LocalDate.of(1987, 5, 15), LocalDate.of(2014, 5, 5)),
                createLawyer(lawOffices.get(3), "Чижик", "Евгений", "Колягович", LocalDate.of(1988, 5, 9), LocalDate.of(2000, 5, 10)),
                createLawyer(lawOffices.get(4), "Переселяк", "Илья", "Сергеевич", LocalDate.of(2003, 2, 25), LocalDate.of(2019, 9, 1))
        );

        List<LawyerActivitySphere> lawyerActivitySpheres = Arrays.asList(
                createLawyerActivitySphere("Гражданское право"),
                createLawyerActivitySphere("Уголовное право"),
                createLawyerActivitySphere("Семейное право"),
                createLawyerActivitySphere("Трудовое право"),
                createLawyerActivitySphere("Наследственное право"),
                createLawyerActivitySphere("Жилищное право"),
                createLawyerActivitySphere("Административное право"),
                createLawyerActivitySphere("Страховое право")
        );

        List<Orientation> orientations = Arrays.asList(
                createOrientation(lawyers.get(0), lawyerActivitySpheres.get(0)),
                createOrientation(lawyers.get(0), lawyerActivitySpheres.get(1)),
                createOrientation(lawyers.get(1), lawyerActivitySpheres.get(1)),
                createOrientation(lawyers.get(1), lawyerActivitySpheres.get(2)),
                createOrientation(lawyers.get(1), lawyerActivitySpheres.get(4)),
                createOrientation(lawyers.get(2), lawyerActivitySpheres.get(3)),
                createOrientation(lawyers.get(2), lawyerActivitySpheres.get(4)),
                createOrientation(lawyers.get(3), lawyerActivitySpheres.get(1)),
                createOrientation(lawyers.get(3), lawyerActivitySpheres.get(4)),
                createOrientation(lawyers.get(3), lawyerActivitySpheres.get(5)),
                createOrientation(lawyers.get(4), lawyerActivitySpheres.get(0)),
                createOrientation(lawyers.get(4), lawyerActivitySpheres.get(1))
        );

        List<ServiceType> serviceTypes = Arrays.asList(
                createServiceType("Консультация"),
                createServiceType("Подача иска"),
                createServiceType("Защита в суде"),
                createServiceType("Подача аппеляции"),
                createServiceType("Подача заявления")
        );

        List<Client> clients = Arrays.asList(
                createClient("Гурский", "Алексей", "Никитович", LocalDate.of(2004, 4, 22), "333-33-33"),
                createClient("Сергун", "Егор", "Егорович", LocalDate.of(2002, 8, 23), "666-66-66"),
                createClient("Клундук", "Даниил", "Евгеньевич", LocalDate.of(2004, 6, 30), "222-22-22"),
                createClient("Чижик", "Владислав", "Артемович", LocalDate.of(2006, 7, 14), "111-11-11"),
                createClient("Гурский", "Иван", "Матвеевич", LocalDate.of(1998, 5, 14), "888-88-88")
        );

        List<ClientFolder> clientFolders = Arrays.asList(
                createCLientFolder(clients.get(0), ClientFolderStatus.valueOf("ACTIVE")),
                createCLientFolder(clients.get(1), ClientFolderStatus.valueOf("ACTIVE")),
                createCLientFolder(clients.get(2), ClientFolderStatus.valueOf("ARCHIVED")),
                createCLientFolder(clients.get(3), ClientFolderStatus.valueOf("ACTIVE")),
                createCLientFolder(clients.get(4), ClientFolderStatus.valueOf("ACTIVE"))
        );

        List<PaperWorkType> paperWorkTypes = Arrays.asList(
                createPaperworkType("Заявление"),
                createPaperworkType("Иск"),
                createPaperworkType("Дело"),
                createPaperworkType("Аппеляционный иск"),
                createPaperworkType("Заявление об ускорении рассмотрения дела"),
                createPaperworkType("Заявление о вызове свидетелей"),
                createPaperworkType("Кассационная жалоба на приговор суда"),
                createPaperworkType("Ходатайство об исключении недопустимого доказательства"),
                createPaperworkType("Ходатайтво об изменении квалификации предъявленного обвинения"),
                createPaperworkType("Заявление о привлечении третьего лица"),
                createPaperworkType("Жалоба"),
                createPaperworkType("Заявление об отводе судьи")
        );

        List<CourtType> courtTypes = Arrays.asList(
                createCourtType("Верховный"),
                createCourtType("Кассационный"),
                createCourtType("Аппеляционный"),
                createCourtType("Районный"),
                createCourtType("Городской")
        );

        List<Judge> judges = Arrays.asList(
                createJudge("Ажгинь", "Данила", "Ярославов", LocalDate.of(2000, 4, 17), LocalDate.of(2012, 7, 15)),
                createJudge("Бельский", "Артем", "Русланов", LocalDate.of(2001, 6, 1), LocalDate.of(2014, 11, 30)),
                createJudge("Сороко", "Полина", "Севастьянова", LocalDate.of(1999, 3, 8), LocalDate.of(2018, 5, 17)),
                createJudge("Яроцкий", "Яков", "Артемов", LocalDate.of(1999, 6, 12), LocalDate.of(2017, 7, 7)),
                createJudge("Емельянова", "Елизавета", "Дмитриевна", LocalDate.of(1997, 6, 6), LocalDate.of(2020, 7, 14))
        );

        List<Court> courts = Arrays.asList(
                createCourt(courtTypes.get(3), "Суд Заводского района г.Минска", "220107, г. Минск, пр. Партизанский, 75а"),
                createCourt(courtTypes.get(3), "Суд Ленинского района г. Минска", "220027, г. Минск, ул. Семашко, 33"),
                createCourt(courtTypes.get(2), "Минский областной суд", "220050, г. Минск, ул. Свердлова, 3"),
                createCourt(courtTypes.get(0), "Верховный Суд Республики Беларусь", "220030, г. Минск, ул. Ленина, 28"),
                createCourt(courtTypes.get(0), "Минский городской суд", "220092, г. Минск, ул. Дунина-Марцинкевича 1")
        );

        List<Paperwork> paperworks = Arrays.asList(
                createPaperwork(paperWorkTypes.get(0), clientFolders.get(1), courts.get(0), judges.get(0), "Кража конфет из автомата", "file:///askomar/paperworks/1.docx"),
                createPaperwork(paperWorkTypes.get(1), clientFolders.get(2), courts.get(2), judges.get(1), "Ударила мужа сковородой", "file:///askomar/paperworks/2.docx"),
                createPaperwork(paperWorkTypes.get(2), clientFolders.get(3), courts.get(1), judges.get(3), "Просверлил дырку в потолке к соседу", "file:///askomar/paperworks/3.doc"),
                createPaperwork(paperWorkTypes.get(3), clientFolders.get(1), courts.get(4), judges.get(2), "Налепил жевачку в лифте на кнопку", "file:///askomar/paperworks/4.doc"),
                createPaperwork(paperWorkTypes.get(0), clientFolders.get(1), courts.get(0), judges.get(0), "Удалил базу данных", "file:///askomar/paperworks/5.docx"),
                createPaperwork(paperWorkTypes.get(3), clientFolders.get(1), courts.get(4), judges.get(2), "Спал на паре", "file:///askomar/paperworks/6.doc"),
                createPaperwork(paperWorkTypes.get(3), clientFolders.get(1), courts.get(4), judges.get(2), "Дали слишком горячий чай", "file:///askomar/paperworks/7.doc"),
                createPaperwork(paperWorkTypes.get(0), clientFolders.get(1), courts.get(4), judges.get(3), "Преподаватель поставил слишком низкую отметку", "file:///askomar/paperworks/8.doc"),
                createPaperwork(paperWorkTypes.get(3), clientFolders.get(1), courts.get(1), judges.get(2), "Наступил на ногу в метро", "file:///askomar/paperworks/9.docx"),
                createPaperwork(paperWorkTypes.get(2), clientFolders.get(1), courts.get(4), judges.get(2), "Забирало НЛО из зоны 51", "file:///askomar/paperworks/10.docx")
        );

        List<Service> services = Arrays.asList(
                createService(serviceTypes.get(2), lawyers.get(1), paperworks.get(2), new BigDecimal("40000.00")),
                createService(serviceTypes.get(0), lawyers.get(0), paperworks.get(0), new BigDecimal(2000.00)),
                createService(serviceTypes.get(3), lawyers.get(1), paperworks.get(1), new BigDecimal(500.00)),
                createService(serviceTypes.get(1), lawyers.get(1), paperworks.get(3), new BigDecimal(500000.00))
        );

        /**
         * ############################################################################################################
         *  Law office CRUD
         */
        try {
            LawOfficeService lawOfficeService = LawOfficeServiceImpl.getInstance();
            lawOffices.stream()
                    .forEach(lawOffice -> {
                        try {
                            lawOfficeService.create(lawOffice);
                        } catch (ParameterIsEmpty e) {
                            logger.error("Exception when try to initialise db by law offices");
                        }
                    });

            List<LawOffice> allOffices = lawOfficeService.findAll();

            LawOffice oneOffice = lawOfficeService.findById(allOffices.get(3).getId());

            oneOffice.setAddress("MODIFIED");
            oneOffice.setName("MODIFIED");
            int updateOfficeResult = lawOfficeService.update(oneOffice);

            //int deleteOfficeResult = lawOfficeService.delete(oneOffice);

            /**
             * Lawyer's CRUD
             */
            LawyerService lawyerService = LawyerServiceImpl.getInstance();
            lawyers.stream()
                    .forEach(lawyer -> {
                        try {
                            lawyerService.create(lawyer, oneOffice.getId());
                        } catch (ParameterIsEmpty | ResourceNotFoundException e) {
                            logger.error("Exception when try to initialise db by lawyers");
                        }
                    });

            List<Lawyer> allLawyers = lawyerService.findAll();

            Lawyer oneLawyer = lawyerService.findById(allLawyers.get(3).getId());

            oneLawyer.setName("MODIFIED");
            oneLawyer.setPatronymic("MODIFIED");
            int updateLawyerResult = lawyerService.update(oneLawyer);

            //  int deleteLawyerResult = lawyerService.delete(oneLawyer);

            /**
             * LawyerActivitySpheres CRUD
             */
            LawyerActivitySphereService lawyerActivitySphereService = LawyerActivitySphereServiceImpl.getInstance();
            lawyerActivitySpheres.stream()
                    .forEach(sphere -> {
                        try {
                            lawyerActivitySphereService.create(sphere);
                        } catch (ParameterIsEmpty e) {
                            logger.error("Exception when try to initialise db by lawyer activity spheres");
                        }
                    });

            List<LawyerActivitySphere> allActivitySpheres = lawyerActivitySphereService.findAll();

            LawyerActivitySphere oneActivitySphere = lawyerActivitySphereService.findById(allActivitySpheres.get(3).getId());

            oneActivitySphere.setName("MODIFIED");
            int updateActivitySphereResult = lawyerActivitySphereService.update(oneActivitySphere);

            //  int deleteActivitySphereResult = lawyerActivitySphereService.delete(oneActivitySphere);

            /**
             * Orientation CRUD
             */
            OrientationService orientationService = OrientationServiceImpl.getInstance();
            orientations.stream()
                    .forEach(orientation -> {
                        try {
                            orientationService.create(orientation, oneLawyer.getId(), oneActivitySphere.getId());
                        } catch (ParameterIsEmpty | ResourceNotFoundException e) {
                            logger.error("Exception when try to initialise db by orientations");
                        }
                    });

            List<Orientation> allOrientations = orientationService.findAll();

            Orientation oneOrientation = orientationService.findById(allOrientations.get(3).getId());

            oneOrientation.setLawyer(oneLawyer);
            oneOrientation.setLawyerActivitySphere(oneActivitySphere);
            int updateOrientationResult = orientationService.update(oneOrientation);

            //    int deleteOrientationResult = orientationService.delete(oneOrientation);

            /**
             * Service Type CRUD
             */

            ServiceTypeService serviceTypeService = ServiceTypeServiceImpl.getInstance();
            serviceTypes.stream()
                    .forEach(serviceType -> {
                        try {
                            serviceTypeService.create(serviceType);
                        } catch (ParameterIsEmpty e) {
                            logger.error("Exception when try to initialise db by service types");
                        }
                    });

            List<ServiceType> allServiceTypes = serviceTypeService.findAll();

            ServiceType oneServiceType = serviceTypeService.findById(allServiceTypes.get(3).getId());

            oneServiceType.setName("MODIFIED");
            int updateServiceTypeResult = serviceTypeService.update(oneServiceType);

            // int deleteServiceTypeResult = serviceTypeService.delete(oneServiceType);

            /**
             * Client CRUD
             */
            ClientService clientService = ClientServiceImpl.getInstance();
            clients.stream()
                    .forEach(client -> {
                        try {
                            clientService.create(client);
                        } catch (ParameterIsEmpty e) {
                            logger.error("Exception when try to initialise db by client services");
                        }
                    });

            List<Client> allClients = clientService.findAll();

            Client oneClient = clientService.findById(allClients.get(3).getId());

            oneClient.setSurname("MODIFIED");
            oneClient.setName("MODIFIED");
            int updateClientResult = clientService.update(oneClient);

            // int deleteClientResult = clientService.delete(oneClient);


            /**
             * ClientFolder CRUD
             */
            ClientFolderService clientFolderService = ClientFolderServiceImpl.getInstance();
            clientFolders.stream()
                    .forEach(folder -> {
                        try {
                            clientFolderService.create(folder, oneClient.getId());
                        } catch (ParameterIsEmpty | ResourceNotFoundException e) {
                            logger.error("Exception when try to initialise db by client folders");
                        }
                    });

            List<ClientFolder> allClientFolders = clientFolderService.findAll();

            ClientFolder oneClientFolder = clientFolderService.findById(allClientFolders.get(3).getId());

            oneClientFolder.setStatus(ClientFolderStatus.ACTIVE);
            int updateClientFolderResult = clientFolderService.update(oneClientFolder);

            // int deleteClientFolderResult = clientFolderService.delete(oneClientFolder);

            /**
             * Paperwork types CRUD
             */
            PaperworkTypeService paperworkTypeService = PaperworkTypeServiceImpl.getInstance();
            paperWorkTypes.stream()
                    .forEach(paperWorkType -> {
                        try {
                            paperworkTypeService.create(paperWorkType);
                        } catch (ParameterIsEmpty e) {
                            logger.error("Exception when try to initialise db by paperwork types");
                        }
                    });

            List<PaperWorkType> allPaperworkTypes = paperworkTypeService.findAll();

            PaperWorkType onePaperworkType = paperworkTypeService.findById(allPaperworkTypes.get(3).getId());

            onePaperworkType.setName("MODIFIED");
            int updatePaperworkTypeResult = paperworkTypeService.update(onePaperworkType);

            // int deletePaperworkTypeResult = paperworkTypeService.delete(onePaperworkType);

            /**
             * CourtType CRUD
             */
            CourtTypeService courtTypeService = CourtTypeServiceImpl.getInstance();
            courtTypes.stream()
                    .forEach(courtType -> {
                        try {
                            courtTypeService.create(courtType);
                        } catch (ParameterIsEmpty e) {
                            logger.error("Exception when try to initialise db by court type services");
                        }
                    });

            List<CourtType> allCourtTypes = courtTypeService.findAll();

            CourtType oneCourtType = courtTypeService.findById(allCourtTypes.get(3).getId());

            oneCourtType.setName("MODIFIED");
            int updateCourtTypeResult = courtTypeService.update(oneCourtType);

            // int deleteCourtTypeResult = courtTypeService.delete(oneCourtType);

            /**
             * Judge CRUD
             */
            JudgeService judgeService = JudgeServiceImpl.getInstance();
            judges.stream()
                    .forEach(judge -> {
                        try {
                            judgeService.create(judge);
                        } catch (ParameterIsEmpty e) {
                            logger.error("Exception when try to initialise db by judges");
                        }
                    });

            List<Judge> allJudges = judgeService.findAll();

            Judge oneJudge = judgeService.findById(allJudges.get(3).getId());

            oneJudge.setSurname("MODIFIED");
            oneJudge.setName("MODIFIED");
            int updateJudgeResult = judgeService.update(oneJudge);

            // int deleteJudgeResult = judgeService.delete(oneJudge);

            /**
             * Court CRUD
             */
            CourtService courtService = CourtServiceImpl.getInstance();
            courts.stream()
                    .forEach(court -> {
                        try {
                            courtService.create(court, oneCourtType.getId());
                        } catch (ParameterIsEmpty | ResourceNotFoundException e) {
                            logger.error("Exception when try to initialise db by courts");
                        }
                    });

            List<Court> allCourts = courtService.findAll();

            Court oneCourt = courtService.findById(allCourts.get(3).getId());

            oneCourt.setAddress("MODIFIED");
            oneCourt.setName("MODIFIED");
            int updateCourtResult = courtService.update(oneCourt);

            // int deleteCourtResult = courtService.delete(oneCourt);

            /**
             * Paperwork CRUD
             */
            PaperworkService paperworkService = PaperworkServiceImpl.getInstance();
            paperworks.stream()
                    .forEach(paperwork -> {
                        try {
                            paperworkService.create(paperwork, onePaperworkType.getId(), oneClientFolder.getId(), oneCourt.getId(), oneJudge.getId());
                        } catch (ParameterIsEmpty | ResourceNotFoundException e) {
                            logger.error("Exception when try to initialise db by paperworks");

                        }
                    });

            List<Paperwork> allPaperworks = paperworkService.findAll();

            Paperwork onePaperwork = paperworkService.findById(allPaperworks.get(3).getId());

            onePaperwork.setTitle("MODIFIED");
            onePaperwork.setUrl("MODIFIED");
            int updatePaperworkResult = paperworkService.update(onePaperwork);

            //  int deletePaperworkResult = paperworkService.delete(onePaperwork);

        } catch (ParameterIsEmpty | ResourceNotFoundException parameterIsEmpty) {
            logger.error(parameterIsEmpty);
        }
    }

    private static LawOffice createLawOffice(String name, String address) {
        LawOffice lawOffice = new LawOffice();
        lawOffice.setName(name);
        lawOffice.setAddress(address);
        return lawOffice;
    }

    private static Lawyer createLawyer(LawOffice lawOffice, String surname, String name, String patronymic, LocalDate dob, LocalDate experienceSince) {
        Lawyer lawyer = new Lawyer();
        lawyer.setLawOffice(lawOffice);
        lawyer.setSurname(surname);
        lawyer.setName(name);
        lawyer.setPatronymic(patronymic);
        lawyer.setDob(dob);
        lawyer.setExperienceSince(experienceSince);
        return lawyer;
    }

    private static LawyerActivitySphere createLawyerActivitySphere(String name) {
        LawyerActivitySphere lawyerActivitySphere = new LawyerActivitySphere();
        lawyerActivitySphere.setName(name);
        return lawyerActivitySphere;
    }

    private static Orientation createOrientation(Lawyer lawyer, LawyerActivitySphere lawyerActivitySphere) {
        Orientation orientation = new Orientation();
        orientation.setLawyer(lawyer);
        orientation.setLawyerActivitySphere(lawyerActivitySphere);
        return orientation;
    }

    private static ServiceType createServiceType(String name) {
        ServiceType serviceType = new ServiceType();
        serviceType.setName(name);
        return serviceType;
    }

    private static Client createClient(String surname, String name, String patronymic, LocalDate dob, String telephone) {
        Client client = new Client();
        client.setSurname(surname);
        client.setName(name);
        client.setPatronymic(patronymic);
        client.setDob(dob);
        client.setTelephone(telephone);
        return client;
    }

    private static ClientFolder createCLientFolder(Client client, ClientFolderStatus status) {
        ClientFolder clientFolder = new ClientFolder();
        clientFolder.setClient(client);
        clientFolder.setStatus(status);
        return clientFolder;
    }

    private static PaperWorkType createPaperworkType(String name) {
        PaperWorkType paperWorkType = new PaperWorkType();
        paperWorkType.setName(name);
        return paperWorkType;
    }

    private static CourtType createCourtType(String name) {
        CourtType courtType = new CourtType();
        courtType.setName(name);
        return courtType;
    }

    private static Judge createJudge(String surname, String name, String patronymic, LocalDate dob, LocalDate experienceSince) {
        Judge judge = new Judge();
        judge.setSurname(surname);
        judge.setName(name);
        judge.setPatronymic(patronymic);
        judge.setDob(dob);
        judge.setExperienceSince(experienceSince);
        return judge;
    }

    private static Court createCourt(CourtType type, String name, String address) {
        Court court = new Court();
        court.setType(type);
        court.setName(name);
        court.setAddress(address);
        return court;
    }

    private static Paperwork createPaperwork(PaperWorkType type, ClientFolder folder, Court court, Judge judge, String title, String url) {
        Paperwork paperwork = new Paperwork();
        paperwork.setType(type);
        paperwork.setFolder(folder);
        paperwork.setCourt(court);
        paperwork.setJudge(judge);
        paperwork.setTitle(title);
        paperwork.setUrl(url);
        return paperwork;
    }

    private static Service createService(ServiceType type, Lawyer lawyer, Paperwork paperwork, BigDecimal cost) {
        Service service = new Service();
        service.setType(type);
        service.setLawyer(lawyer);
        service.setPaperWork(paperwork);
        service.setCost(cost);
        return service;
    }
}
