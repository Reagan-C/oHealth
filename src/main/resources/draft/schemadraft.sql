
 create table app_user (
        date_of_birth date not null,
        created_on datetime(6) not null,
        id bigint not null auto_increment,
        updated_on datetime(6) not null,
        address varchar(255) not null,
        email_address varchar(255) not null,
        first_name varchar(255) not null,
        gender varchar(255) not null,
        last_name varchar(255) not null,
        phone_number varchar(255) not null,
        primary key (id)
    ) engine=InnoDB

    create table appointment (
            appointment_date date not null,
            doctor_id bigint,
            id bigint not null auto_increment,
            patient_id bigint,
            primary key (id)
    ) engine=InnoDB

    create table chat_message (
           app_user_id bigint,
           id bigint not null auto_increment,
           sent_at datetime(6) not null,
           content varchar(255),
           primary key (id)
    ) engine=InnoDB

    create table doctor (
            id bigint not null auto_increment,
            role_id bigint,
            user_id bigint,
            primary key (id)
    ) engine=InnoDB

     create table manager (
            id bigint not null auto_increment,
            user_id bigint,
            primary key (id)
     ) engine=InnoDB

    create table manager_roles (
           manager_id bigint not null,
           role_id bigint not null
       ) engine=InnoDB

    create table medical_record (
            id bigint not null auto_increment,
            patient_id bigint not null,
            condition_name varchar(255),
            created_on varbinary(255) not null,
            updated_on varbinary(255) not null,
            primary key (id)
        ) engine=InnoDB

     create table patient (
            id bigint not null auto_increment,
            role_id bigint,
            user_id bigint,
            primary key (id)
     ) engine=InnoDB

     create table qualification (
            app_user_id bigint,
            created_on datetime(6) not null,
            id bigint not null auto_increment,
            updated_on datetime(6) not null,
            cert_number varchar(255) not null,
            name varchar(255) not null,
            primary key (id)
        ) engine=InnoDB

     create table roles (
             created_on datetime(6) not null,
             id bigint not null auto_increment,
             updated_on datetime(6) not null,
             code varchar(255) not null,
             title varchar(255) not null,
             primary key (id)
         ) engine=InnoDB

     create table vitals (
             temperature float(53) not null,
             weight float(53) not null,
             checked_on datetime(6) not null,
             id bigint not null auto_increment,
             patient_id bigint,
             blood_pressure varchar(255) not null,
             primary key (id)
         ) engine=InnoDB



 alter table doctor
        add constraint FKrmgt77qsoesjafmojpd3pq3pu
        foreign key (role_id)
        references roles (id)
 Hibernate:
     alter table doctor
        add constraint FKqfmt6yrrw6gnryw87otq45b65
        foreign key (user_id)
        references app_user (id)

alter table manager
       add constraint FKf7rwyhrj5btqdmqtvusu6ubh0
       foreign key (user_id)
       references app_user (id)

  alter table manager_roles
       add constraint FKgub5oh3q2b0w0mjf3pig6r7be
       foreign key (role_id)
       references roles (id)
Hibernate:
    alter table manager_roles
       add constraint FKso1dp4tmjqlsqq8jh0tgrcrf3
       foreign key (manager_id)
       references manager (id)

 alter table medical_record
       add constraint FKt0lf3feuiurr73bpln2n6x0v
       foreign key (patient_id)
       references patient (id)

  alter table patient
       add constraint FKjjbq0qa05l73e0yrokklh16v4
       foreign key (role_id)
       references roles (id)
Hibernate:
    alter table patient
       add constraint FKocqalxvo9u3uxwggs6ay2qmpy
       foreign key (user_id)
       references app_user (id)

alter table qualification
       add constraint FKp3qi821379uveoia8q0nwkiw9
       foreign key (app_user_id)
       references app_user (id)

 alter table vitals
       add constraint FK7ag0rkboe8dqx0ieud7vfo0lq
       foreign key (patient_id)
       references patient (id)