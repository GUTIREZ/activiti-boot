DROP TABLE IF EXISTS `attachments`;
CREATE TABLE `attachments` (
  `id` int(4) NOT NULL,
  `container_id` int(4) DEFAULT NULL,
  `container_type` varchar(30) DEFAULT NULL,
  `filename` varchar(255) NOT NULL,
  `disk_filename` varchar(255) NOT NULL,
  `filesize` int(8) NOT NULL DEFAULT '0',
  `content_type` varchar(255) DEFAULT NULL,
  `digest` varchar(64) NOT NULL,
  `downloads` int(4) NOT NULL DEFAULT '0',
  `author_id` int(4) NOT NULL DEFAULT '0',
  `created_on` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `description` varchar(255) DEFAULT NULL,
  `disk_directory` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE boards (
id int(4) NOT NULL,
project_id int(4) NOT NULL,
name varchar(255)   NOT NULL,
description varchar(255),
position int(4),
topics_count int(4)  NOT NULL,
messages_count int(4)  NOT NULL,
last_message_id int(4),
parent_id int(4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE custom_fields (
id int(4)  NOT NULL,
type varchar(30)   NOT NULL,
name varchar(30)   NOT NULL,
field_format varchar(30)   NOT NULL,
possible_values text,
regexpp varchar(255), -- regexp 
min_length int(4),
max_length int(4),
is_required TINYINT(4) NOT NULL,
is_for_all TINYINT NOT NULL,
is_filter TINYINT NOT NULL,
position int(4),
searchable TINYINT,
default_value text,
editable TINYINT,
visible TINYINT,
multiple TINYINT,
format_store text,
description text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE custom_fields_projects (
custom_field_id int4 DEFAULT 0 NOT NULL,
project_id int4 DEFAULT 0 NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE custom_values (
id int(4)  NOT NULL,
customized_type varchar(30)  NOT NULL,
customized_id int(4) DEFAULT 0 NOT NULL,
custom_field_id int(4) DEFAULT 0 NOT NULL,
value text
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE custom_fields_roles (
custom_field_id int(4) NOT NULL,
role_id int(4) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE custom_fields_trackers (
custom_field_id int(4) DEFAULT 0 NOT NULL,
tracker_id int(4) DEFAULT 0 NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE documents (
id int(4)NOT NULL,
project_id int(4) NOT NULL,
category_id int(4) NOT NULL,
title varchar(255) NOT NULL,
description text,
created_on timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE email_addresses (
id int(4)  NOT NULL,
user_id int(4) NOT NULL,
address varchar(255) NOT NULL,
is_default bool DEFAULT false NOT NULL,
notify bool DEFAULT true NOT NULL,
created_on timestamp(6) NOT NULL,
updated_on timestamp(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE enabled_modules (
id int(4) NOT NULL,
project_id int(4),
name varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE enumerations (
id int(4) NOT NULL,
name varchar(30)   NOT NULL,
position int(4),
is_default TINYINT,
type varchar(255),
active bool DEFAULT true NOT NULL,
project_id int(4),
parent_id int(4),
position_name varchar(30)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE groups_users (
group_id int(4) NOT NULL,
user_id int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE issue_relations (
id int(4) NOT NULL,
issue_from_id int(4) NOT NULL,
issue_to_id int(4) NOT NULL,
relation_type varchar(255)   NOT NULL,
delay int(4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE issue_statuses (
id int(4) NOT NULL,
name varchar(30)   NOT NULL,
is_closed TINYINT NOT NULL,
position int(4),
default_done_ratio int(4),
next_role varchar(32),
tracker_id varchar(32)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE issues (
id int(4) NOT NULL,
tracker_id int(4) NOT NULL,
project_id int(4) NOT NULL,
subject varchar(255) NOT NULL,
description text,
due_date date,
category_id int(4),
status_id int(4) NOT NULL,
assigned_to_id int(4),
priority_id int(4) NOT NULL,
fixed_version_id int(4),
author_id int(4) NOT NULL,
lock_version int(4) DEFAULT 0 NOT NULL,
created_on timestamp(6),
updated_on timestamp(6),
start_date date,
done_ratio int(4) DEFAULT 0 NOT NULL,
estimated_hours float8,
parent_id int(4),
root_id int(4),
lft int(4),
rgt int(4),
is_private bool DEFAULT false NOT NULL,
closed_on timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE journal_details (
id int(4) NOT NULL,
journal_id int(4) NOT NULL,
property varchar(30)   NOT NULL,
prop_key varchar(30)   NOT NULL,
old_value text,
value text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE journals (
id int(4) DEFAULT nextval('journals_id_seq'::regclass) NOT NULL,
journalized_id int(4) DEFAULT 0 NOT NULL,
journalized_type varchar(30)   NOT NULL,
user_id int(4) DEFAULT 0 NOT NULL,
notes text,
created_on timestamp(6) NOT NULL,
private_notes bool DEFAULT false NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE member_roles (
id int(4) NOT NULL,
member_id int(4) NOT NULL,
role_id int(4) NOT NULL,
inherited_from int(4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE members (
id int(4) NOT NULL,
user_id int(4) DEFAULT 0 NOT NULL,
project_id int(4) DEFAULT 0 NOT NULL,
created_on timestamp(6),
mail_notification TINYINT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE messages (
id int(4) NOT NULL,
board_id int(4) NOT NULL,
parent_id int(4),
subject varchar(255)   NOT NULL,
content text,
author_id int(4),
replies_count int(4) DEFAULT 0 NOT NULL,
last_reply_id int(4),
created_on timestamp(6) NOT NULL,
updated_on timestamp(6) NOT NULL,
locked bool DEFAULT false,
sticky int(4) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE projects (
id int(4) NOT NULL,
name varchar(255)   NOT NULL,
description text,
homepage varchar(255)  ,
is_public TINYINT NOT NULL,
parent_id int(4),
created_on timestamp(6),
updated_on timestamp(6),
identifier varchar(255),
status int(4) NOT NULL,
lft int(4),
rgt int(4),
inherit_members TINYINT NOT NULL,
default_version_id int(4),
default_assigned_to_id int(4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE projects_trackers (
project_id int(4) DEFAULT 0 NOT NULL,
tracker_id int(4) DEFAULT 0 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE roles (
id int(4) NOT NULL,
name varchar(30)   NOT NULL,
position int(4),
assignable TINYINT ,
builtin int(4) DEFAULT 0 NOT NULL,
permissions text,
issues_visibility varchar(30) NOT NULL,
users_visibility varchar(30)  NOT NULL,
time_entries_visibility varchar(30)  NOT NULL,
all_roles_managed TINYINT NOT NULL,
settings text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE trackers (
id int(4)  NOT NULL,
name varchar(30)   NOT NULL,
is_in_chlog TINYINT NOT NULL,
position int(4),
is_in_roadmap TINYINT NOT NULL,
fields_bits int(4) DEFAULT 0,
default_status_id int(4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_preferences (
id int(4)  NOT NULL,
user_id int(4) DEFAULT 0 NOT NULL,
others text,
hide_mail TINYINT,
time_zone varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE users (
id int(4) NOT NULL,
login varchar(255)   NOT NULL,
hashed_password varchar(40)   NOT NULL,
firstname varchar(30)   NOT NULL,
lastname varchar(255)   NOT NULL,
admin bool DEFAULT false NOT NULL,
status int(4) DEFAULT 1 NOT NULL,
last_login_on timestamp(6),
language varchar(5)  ,
auth_source_id int(4),
created_on timestamp(6),
updated_on timestamp(6),
type varchar(255),
identity_url varchar(255),
mail_notification varchar(255)   NOT NULL,
salt varchar(64),
must_change_passwd TINYINT NOT NULL,
passwd_changed_on timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE watchers (
id int(4) NOT NULL,
watchable_type varchar(255)   NOT NULL,
watchable_id int(4) DEFAULT 0 NOT NULL,
user_id int(4)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `workflows` (
  `id` int(4) NOT NULL,
  `tracker_id` int(4) NOT NULL DEFAULT '0',
  `old_status_id` int(4) NOT NULL DEFAULT '0',
  `new_status_id` int(4) NOT NULL DEFAULT '0',
  `role_id` int(4) NOT NULL DEFAULT '0',
  `assignee` tinyint(1) NOT NULL,
  `author` tinyint(1) NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  `field_name` varchar(30) DEFAULT NULL,
  `rule` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- ----------------------------
-- Indexes structure for table attachments
-- ----------------------------
CREATE INDEX  index_attachments_on_author_id  ON  attachments ( author_id );
CREATE INDEX  index_attachments_on_container_id_and_container_type  ON  attachments ( container_id ,  container_type );
CREATE INDEX  index_attachments_on_created_on  ON  attachments ( created_on );
CREATE INDEX  index_attachments_on_disk_filename  ON  attachments ( disk_filename );

ALTER TABLE  attachments  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table boards
-- ----------------------------
CREATE INDEX  boards_project_id  ON  boards ( project_id );
CREATE INDEX  index_boards_on_last_message_id  ON  boards ( last_message_id );

-- ----------------------------
-- Primary Key structure for table  boards 
-- ----------------------------
ALTER TABLE  boards  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table custom_fields
-- ----------------------------
CREATE INDEX  index_custom_fields_on_id_and_type  ON  custom_fields ( id ,  type );

-- ----------------------------
-- Primary Key structure for table  custom_fields 
-- ----------------------------
ALTER TABLE  custom_fields  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table custom_fields_projects
-- ----------------------------
CREATE UNIQUE INDEX  index_custom_fields_projects_on_custom_field_id_and_project_id  ON  custom_fields_projects ( custom_field_id ,  project_id );

-- ----------------------------
-- Indexes structure for table custom_fields_roles
-- ----------------------------
CREATE UNIQUE INDEX  custom_fields_roles_ids  ON  custom_fields_roles ( custom_field_id ,  role_id );

-- ----------------------------
-- Indexes structure for table custom_fields_trackers
-- ----------------------------
CREATE UNIQUE INDEX  index_custom_fields_trackers_on_custom_field_id_and_tracker_id  ON  custom_fields_trackers ( custom_field_id ,  tracker_id );

-- ----------------------------
-- Indexes structure for table custom_values
-- ----------------------------
CREATE INDEX  custom_values_customized  ON  custom_values ( customized_type ,  customized_id );
CREATE INDEX  index_custom_values_on_custom_field_id  ON  custom_values ( custom_field_id );

-- ----------------------------
-- Primary Key structure for table  custom_values 
-- ----------------------------
ALTER TABLE  custom_values  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table documents
-- ----------------------------
CREATE INDEX  documents_project_id  ON  documents ( project_id );
CREATE INDEX  index_documents_on_category_id  ON  documents ( category_id );
CREATE INDEX  index_documents_on_created_on  ON  documents ( created_on );

-- ----------------------------
-- Primary Key structure for table  documents 
-- ----------------------------
ALTER TABLE  documents  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table email_addresses
-- ----------------------------
CREATE INDEX  index_email_addresses_on_user_id  ON  email_addresses ( user_id );

-- ----------------------------
-- Primary Key structure for table  email_addresses 
-- ----------------------------
ALTER TABLE  email_addresses  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table enabled_modules
-- ----------------------------
CREATE INDEX  enabled_modules_project_id  ON  enabled_modules ( project_id );

-- ----------------------------
-- Primary Key structure for table  enabled_modules 
-- ----------------------------
ALTER TABLE  enabled_modules  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table enumerations
-- ----------------------------
CREATE INDEX  index_enumerations_on_id_and_type  ON  enumerations ( id ,  type );
CREATE INDEX  index_enumerations_on_project_id  ON  enumerations ( project_id );

-- ----------------------------
-- Primary Key structure for table  enumerations 
-- ----------------------------
ALTER TABLE  enumerations  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table groups_users
-- ----------------------------
CREATE UNIQUE INDEX  groups_users_ids  ON  groups_users ( group_id ,  user_id );

-- ----------------------------
-- Primary Key structure for table  import_items 
-- ----------------------------
ALTER TABLE  import_items  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table issue_relations
-- ----------------------------
CREATE INDEX  index_issue_relations_on_issue_from_id  ON  issue_relations ( issue_from_id );
CREATE UNIQUE INDEX  index_issue_relations_on_issue_from_id_and_issue_to_id  ON  issue_relations ( issue_from_id ,  issue_to_id );
CREATE INDEX  index_issue_relations_on_issue_to_id  ON  issue_relations ( issue_to_id );

-- ----------------------------
-- Primary Key structure for table  issue_relations 
-- ----------------------------
ALTER TABLE  issue_relations  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table issue_statuses
-- ----------------------------
CREATE INDEX  index_issue_statuses_on_is_closed  ON  issue_statuses ( is_closed );
CREATE INDEX  index_issue_statuses_on_position  ON  issue_statuses ( position );

-- ----------------------------
-- Primary Key structure for table  issue_statuses 
-- ----------------------------
ALTER TABLE  issue_statuses  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table issues
-- ----------------------------
CREATE INDEX  index_issues_on_assigned_to_id  ON  issues ( assigned_to_id );
CREATE INDEX  index_issues_on_author_id  ON  issues ( author_id );
CREATE INDEX  index_issues_on_category_id  ON  issues ( category_id );
CREATE INDEX  index_issues_on_created_on  ON  issues ( created_on );
CREATE INDEX  index_issues_on_fixed_version_id  ON  issues ( fixed_version_id );
CREATE INDEX  index_issues_on_parent_id  ON  issues ( parent_id );
CREATE INDEX  index_issues_on_priority_id  ON  issues ( priority_id );
CREATE INDEX  index_issues_on_root_id_and_lft_and_rgt  ON  issues ( root_id ,  lft ,  rgt );
CREATE INDEX  index_issues_on_status_id  ON  issues ( status_id );
CREATE INDEX  index_issues_on_tracker_id  ON  issues ( tracker_id );
CREATE INDEX  issues_project_id  ON  issues ( project_id );

-- ----------------------------
-- Primary Key structure for table  issues 
-- ----------------------------
ALTER TABLE  issues  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table journal_details
-- ----------------------------
CREATE INDEX  journal_details_journal_id  ON  journal_details ( journal_id );

-- ----------------------------
-- Primary Key structure for table  journal_details 
-- ----------------------------
ALTER TABLE  journal_details  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table journals
-- ----------------------------
CREATE INDEX  index_journals_on_created_on  ON  journals ( created_on );
CREATE INDEX  index_journals_on_journalized_id  ON  journals ( journalized_id );
CREATE INDEX  index_journals_on_user_id  ON  journals ( user_id );
CREATE INDEX  journals_journalized_id  ON  journals ( journalized_id ,  journalized_type );

-- ----------------------------
-- Primary Key structure for table  journals 
-- ----------------------------
ALTER TABLE  journals  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table member_roles
-- ----------------------------
CREATE INDEX  index_member_roles_on_inherited_from  ON  member_roles ( inherited_from );
CREATE INDEX  index_member_roles_on_member_id  ON  member_roles ( member_id );
CREATE INDEX  index_member_roles_on_role_id  ON  member_roles ( role_id );

-- ----------------------------
-- Primary Key structure for table  member_roles 
-- ----------------------------
ALTER TABLE  member_roles  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table members
-- ----------------------------
CREATE INDEX  index_members_on_project_id  ON  members ( project_id );
CREATE INDEX  index_members_on_user_id  ON  members ( user_id );
CREATE UNIQUE INDEX  index_members_on_user_id_and_project_id  ON  members ( user_id ,  project_id );

-- ----------------------------
-- Primary Key structure for table  members 
-- ----------------------------
ALTER TABLE  members  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table messages
-- ----------------------------
CREATE INDEX  index_messages_on_author_id  ON  messages ( author_id );
CREATE INDEX  index_messages_on_created_on  ON  messages ( created_on );
CREATE INDEX  index_messages_on_last_reply_id  ON  messages ( last_reply_id );
CREATE INDEX  messages_board_id  ON  messages ( board_id );
CREATE INDEX  messages_parent_id  ON  messages ( parent_id );

-- ----------------------------
-- Primary Key structure for table  messages 
-- ----------------------------
ALTER TABLE  messages  ADD PRIMARY KEY ( id );


-- ----------------------------
-- Indexes structure for table projects
-- ----------------------------
CREATE INDEX  index_projects_on_lft  ON  projects ( lft );
CREATE INDEX  index_projects_on_rgt  ON  projects ( rgt );

-- ----------------------------
-- Primary Key structure for table  projects 
-- ----------------------------
ALTER TABLE  projects  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table projects_trackers
-- ----------------------------
CREATE INDEX  projects_trackers_project_id  ON  projects_trackers ( project_id );
CREATE UNIQUE INDEX  projects_trackers_unique  ON  projects_trackers ( project_id ,  tracker_id );

-- ----------------------------
-- Primary Key structure for table  roles 
-- ----------------------------
ALTER TABLE  roles  ADD PRIMARY KEY ( id );


-- ----------------------------
-- Primary Key structure for table  trackers 
-- ----------------------------
ALTER TABLE  trackers  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table user_preferences
-- ----------------------------
CREATE INDEX  index_user_preferences_on_user_id  ON  user_preferences ( user_id );

-- ----------------------------
-- Primary Key structure for table  user_preferences 
-- ----------------------------
ALTER TABLE  user_preferences  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Indexes structure for table users
-- ----------------------------
CREATE INDEX  index_users_on_auth_source_id  ON  users ( auth_source_id );
CREATE INDEX  index_users_on_id_and_type  ON  users ( id ,  type );
CREATE INDEX  index_users_on_type  ON  users ( type );

-- ----------------------------
-- Primary Key structure for table  users 
-- ----------------------------
ALTER TABLE  users  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Primary Key structure for table  watchers 
-- ----------------------------
ALTER TABLE  watchers  ADD PRIMARY KEY ( id );

-- ----------------------------
-- Primary Key structure for table  workflows 
-- ----------------------------
ALTER TABLE  workflows  ADD PRIMARY KEY ( id );
