/*
Navicat MySQL Data Transfer

Source Server         : 47.98.140.122
Source Server Version : 50640
Source Host           : 47.98.140.122:3306
Source Database       : activiti6UI

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2018-09-17 15:08:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `attachments`
-- ----------------------------
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

-- ----------------------------
-- Records of attachments
-- ----------------------------

-- ----------------------------
-- Table structure for `boards`
-- ----------------------------
DROP TABLE IF EXISTS `boards`;
CREATE TABLE `boards` (
  `id` int(4) NOT NULL,
  `project_id` int(4) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `position` int(4) DEFAULT NULL,
  `topics_count` int(4) NOT NULL,
  `messages_count` int(4) NOT NULL,
  `last_message_id` int(4) DEFAULT NULL,
  `parent_id` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of boards
-- ----------------------------

-- ----------------------------
-- Table structure for `custom_fields`
-- ----------------------------
DROP TABLE IF EXISTS `custom_fields`;
CREATE TABLE `custom_fields` (
  `id` int(4) NOT NULL,
  `type` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `field_format` varchar(30) NOT NULL,
  `possible_values` text,
  `regexpp` varchar(255) DEFAULT NULL,
  `min_length` int(4) DEFAULT NULL,
  `max_length` int(4) DEFAULT NULL,
  `is_required` tinyint(4) NOT NULL,
  `is_for_all` tinyint(4) NOT NULL,
  `is_filter` tinyint(4) NOT NULL,
  `position` int(4) DEFAULT NULL,
  `searchable` tinyint(4) DEFAULT NULL,
  `default_value` text,
  `editable` tinyint(4) DEFAULT NULL,
  `visible` tinyint(4) DEFAULT NULL,
  `multiple` tinyint(4) DEFAULT NULL,
  `format_store` text,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom_fields
-- ----------------------------

-- ----------------------------
-- Table structure for `custom_fields_projects`
-- ----------------------------
DROP TABLE IF EXISTS `custom_fields_projects`;
CREATE TABLE `custom_fields_projects` (
  `custom_field_id` int(11) NOT NULL DEFAULT '0',
  `project_id` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom_fields_projects
-- ----------------------------

-- ----------------------------
-- Table structure for `custom_fields_roles`
-- ----------------------------
DROP TABLE IF EXISTS `custom_fields_roles`;
CREATE TABLE `custom_fields_roles` (
  `custom_field_id` int(4) NOT NULL,
  `role_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom_fields_roles
-- ----------------------------

-- ----------------------------
-- Table structure for `custom_fields_trackers`
-- ----------------------------
DROP TABLE IF EXISTS `custom_fields_trackers`;
CREATE TABLE `custom_fields_trackers` (
  `custom_field_id` int(4) NOT NULL DEFAULT '0',
  `tracker_id` int(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom_fields_trackers
-- ----------------------------

-- ----------------------------
-- Table structure for `custom_values`
-- ----------------------------
DROP TABLE IF EXISTS `custom_values`;
CREATE TABLE `custom_values` (
  `id` int(4) NOT NULL,
  `customized_type` varchar(30) NOT NULL,
  `customized_id` int(4) NOT NULL DEFAULT '0',
  `custom_field_id` int(4) NOT NULL DEFAULT '0',
  `value` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of custom_values
-- ----------------------------

-- ----------------------------
-- Table structure for `documents`
-- ----------------------------
DROP TABLE IF EXISTS `documents`;
CREATE TABLE `documents` (
  `id` int(4) NOT NULL,
  `project_id` int(4) NOT NULL,
  `category_id` int(4) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text,
  `created_on` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of documents
-- ----------------------------

-- ----------------------------
-- Table structure for `email_addresses`
-- ----------------------------
DROP TABLE IF EXISTS `email_addresses`;
CREATE TABLE `email_addresses` (
  `id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  `address` varchar(255) NOT NULL,
  `is_default` tinyint(1) NOT NULL DEFAULT '0',
  `notify` tinyint(1) NOT NULL DEFAULT '1',
  `created_on` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `updated_on` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of email_addresses
-- ----------------------------

-- ----------------------------
-- Table structure for `enabled_modules`
-- ----------------------------
DROP TABLE IF EXISTS `enabled_modules`;
CREATE TABLE `enabled_modules` (
  `id` int(4) NOT NULL,
  `project_id` int(4) DEFAULT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enabled_modules
-- ----------------------------

-- ----------------------------
-- Table structure for `enumerations`
-- ----------------------------
DROP TABLE IF EXISTS `enumerations`;
CREATE TABLE `enumerations` (
  `id` int(4) NOT NULL,
  `name` varchar(30) NOT NULL,
  `position` int(4) DEFAULT NULL,
  `is_default` tinyint(4) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `project_id` int(4) DEFAULT NULL,
  `parent_id` int(4) DEFAULT NULL,
  `position_name` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enumerations
-- ----------------------------

-- ----------------------------
-- Table structure for `groups_users`
-- ----------------------------
DROP TABLE IF EXISTS `groups_users`;
CREATE TABLE `groups_users` (
  `group_id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of groups_users
-- ----------------------------

-- ----------------------------
-- Table structure for `import_items`
-- ----------------------------
DROP TABLE IF EXISTS `import_items`;
CREATE TABLE `import_items` (
  `id` int(4) NOT NULL,
  `import_id` int(4) NOT NULL,
  `position` int(4) NOT NULL,
  `obj_id` int(4) DEFAULT NULL,
  `message` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of import_items
-- ----------------------------

-- ----------------------------
-- Table structure for `issue_relations`
-- ----------------------------
DROP TABLE IF EXISTS `issue_relations`;
CREATE TABLE `issue_relations` (
  `id` int(4) NOT NULL,
  `issue_from_id` int(4) NOT NULL,
  `issue_to_id` int(4) NOT NULL,
  `relation_type` varchar(255) NOT NULL,
  `delay` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of issue_relations
-- ----------------------------

-- ----------------------------
-- Table structure for `issue_statuses`
-- ----------------------------
DROP TABLE IF EXISTS `issue_statuses`;
CREATE TABLE `issue_statuses` (
  `id` int(4) NOT NULL,
  `name` varchar(30) NOT NULL,
  `is_closed` tinyint(4) NOT NULL,
  `position` int(4) DEFAULT NULL,
  `default_done_ratio` int(4) DEFAULT NULL,
  `next_role` varchar(32) DEFAULT NULL,
  `tracker_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of issue_statuses
-- ----------------------------

-- ----------------------------
-- Table structure for `Issue`
-- ----------------------------
DROP TABLE IF EXISTS `issues`;
CREATE TABLE `issues` (
  `id` int(4) NOT NULL,
  `tracker_id` int(4) NOT NULL,
  `project_id` int(4) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `description` text,
  `due_date` date DEFAULT NULL,
  `category_id` int(4) DEFAULT NULL,
  `status_id` int(4) NOT NULL,
  `assigned_to_id` int(4) DEFAULT NULL,
  `priority_id` int(4) NOT NULL,
  `fixed_version_id` int(4) DEFAULT NULL,
  `author_id` int(4) NOT NULL,
  `lock_version` int(4) NOT NULL DEFAULT '0',
  `created_on` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `updated_on` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `start_date` date DEFAULT NULL,
  `done_ratio` int(4) NOT NULL DEFAULT '0',
  `estimated_hours` double DEFAULT NULL,
  `parent_id` int(4) DEFAULT NULL,
  `root_id` int(4) DEFAULT NULL,
  `lft` int(4) DEFAULT NULL,
  `rgt` int(4) DEFAULT NULL,
  `is_private` tinyint(1) NOT NULL DEFAULT '0',
  `closed_on` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Issue
-- ----------------------------

-- ----------------------------
-- Table structure for `journal_details`
-- ----------------------------
DROP TABLE IF EXISTS `journal_details`;
CREATE TABLE `journal_details` (
  `id` int(4) NOT NULL,
  `journal_id` int(4) NOT NULL,
  `property` varchar(30) NOT NULL,
  `prop_key` varchar(30) NOT NULL,
  `old_value` text,
  `value` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of journal_details
-- ----------------------------

-- ----------------------------
-- Table structure for `journals`
-- ----------------------------
DROP TABLE IF EXISTS `journals`;
CREATE TABLE `journals` (
  `id` int(4) NOT NULL,
  `journalized_id` int(4) NOT NULL DEFAULT '0',
  `journalized_type` varchar(30) NOT NULL,
  `user_id` int(4) NOT NULL DEFAULT '0',
  `notes` text,
  `created_on` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `private_notes` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of journals
-- ----------------------------

-- ----------------------------
-- Table structure for `member_roles`
-- ----------------------------
DROP TABLE IF EXISTS `member_roles`;
CREATE TABLE `member_roles` (
  `id` int(4) NOT NULL,
  `member_id` int(4) NOT NULL,
  `role_id` int(4) NOT NULL,
  `inherited_from` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_roles
-- ----------------------------

-- ----------------------------
-- Table structure for `members`
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL DEFAULT '0',
  `project_id` int(4) NOT NULL DEFAULT '0',
  `created_on` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `mail_notification` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of members
-- ----------------------------

-- ----------------------------
-- Table structure for `messages`
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` int(4) NOT NULL,
  `board_id` int(4) NOT NULL,
  `parent_id` int(4) DEFAULT NULL,
  `subject` varchar(255) NOT NULL,
  `content` text,
  `author_id` int(4) DEFAULT NULL,
  `replies_count` int(4) NOT NULL DEFAULT '0',
  `last_reply_id` int(4) DEFAULT NULL,
  `created_on` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `updated_on` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `locked` tinyint(1) DEFAULT '0',
  `sticky` int(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messages
-- ----------------------------

-- ----------------------------
-- Table structure for `projects`
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `id` int(4) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `homepage` varchar(255) DEFAULT NULL,
  `is_public` tinyint(4) NOT NULL,
  `parent_id` int(4) DEFAULT NULL,
  `created_on` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `updated_on` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `identifier` varchar(255) DEFAULT NULL,
  `status` int(4) NOT NULL,
  `lft` int(4) DEFAULT NULL,
  `rgt` int(4) DEFAULT NULL,
  `inherit_members` tinyint(4) NOT NULL,
  `default_version_id` int(4) DEFAULT NULL,
  `default_assigned_to_id` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of projects
-- ----------------------------

-- ----------------------------
-- Table structure for `projects_trackers`
-- ----------------------------
DROP TABLE IF EXISTS `projects_trackers`;
CREATE TABLE `projects_trackers` (
  `project_id` int(4) NOT NULL DEFAULT '0',
  `tracker_id` int(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of projects_trackers
-- ----------------------------

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(4) NOT NULL,
  `name` varchar(30) NOT NULL,
  `position` int(4) DEFAULT NULL,
  `assignable` tinyint(4) DEFAULT NULL,
  `builtin` int(4) NOT NULL DEFAULT '0',
  `permissions` text,
  `issues_visibility` varchar(30) NOT NULL,
  `users_visibility` varchar(30) NOT NULL,
  `time_entries_visibility` varchar(30) NOT NULL,
  `all_roles_managed` tinyint(4) NOT NULL,
  `settings` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------

-- ----------------------------
-- Table structure for `trackers`
-- ----------------------------
DROP TABLE IF EXISTS `trackers`;
CREATE TABLE `trackers` (
  `id` int(4) NOT NULL,
  `name` varchar(30) NOT NULL,
  `is_in_chlog` tinyint(4) NOT NULL,
  `position` int(4) DEFAULT NULL,
  `is_in_roadmap` tinyint(4) NOT NULL,
  `fields_bits` int(4) DEFAULT '0',
  `default_status_id` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trackers
-- ----------------------------

-- ----------------------------
-- Table structure for `user_preferences`
-- ----------------------------
DROP TABLE IF EXISTS `user_preferences`;
CREATE TABLE `user_preferences` (
  `id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL DEFAULT '0',
  `others` text,
  `hide_mail` tinyint(4) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_preferences
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(4) NOT NULL,
  `login` varchar(255) NOT NULL,
  `hashed_password` varchar(40) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `status` int(4) NOT NULL DEFAULT '1',
  `last_login_on` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `language` varchar(5) DEFAULT NULL,
  `auth_source_id` int(4) DEFAULT NULL,
  `created_on` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `updated_on` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `type` varchar(255) DEFAULT NULL,
  `identity_url` varchar(255) DEFAULT NULL,
  `mail_notification` varchar(255) NOT NULL,
  `salt` varchar(64) DEFAULT NULL,
  `must_change_passwd` tinyint(4) NOT NULL,
  `passwd_changed_on` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- Table structure for `watchers`
-- ----------------------------
DROP TABLE IF EXISTS `watchers`;
CREATE TABLE `watchers` (
  `id` int(4) NOT NULL,
  `watchable_type` varchar(255) NOT NULL,
  `watchable_id` int(4) NOT NULL DEFAULT '0',
  `user_id` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of watchers
-- ----------------------------
