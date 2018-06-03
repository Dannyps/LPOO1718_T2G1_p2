-- mysql 

CREATE TABLE `scores` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL COMMENT 'The name the player typed when they committed the score',
  `score` int(10) UNSIGNED NOT NULL COMMENT 'The score.',
  `date` datetime NOT NULL,
  `ip` text NOT NULL
) 


ALTER TABLE `scores` ADD PRIMARY KEY (`id`);


ALTER TABLE `scores` MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

