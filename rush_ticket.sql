DROP TABLE IF EXISTS `movie_information`;
CREATE TABLE `movie_information` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`type` VARCHAR(20) NOT NULL,
	`director` VARCHAR(10) NOT NULL,
	`actors` VARCHAR(255) NOT NULL,
	`plot` TEXT NOT NULL,
	`pic` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `name` (`name`)
)

movie_information:
INSERT INTO movie_information(name,type,director,actors,plot,pic) VALUES ("摆渡人","群星贺岁","张嘉佳","梁朝伟,金城武,陈奕迅,杨颖,张榕容,杜鹃,熊黛林,董成鹏,李宇春,鹿晗,马苏,崔志佳,贾玲,李灿森,柳岩,郑开元,大宝,盛鉴,金士杰,喜翔,曹可凡,陈妍希,安又琪,花太郎,毛俊杰,柯佳嬿,欧汉声,纳豆","每座城市都有属于自己的传奇，这座城市的传奇就是“摆渡人”。据说，他们能消除世间痛苦。酒吧老板陈末（梁朝伟 饰）和合伙人管春（金城武 饰），平时看起来吊儿郎当，但其实是“金牌摆渡人”，空手接白刃，人肉千斤顶，只要你“预约”，就“无所不能”。邻居女孩小玉（杨颖 饰）为了偶像马力（陈奕迅 饰），预约了他们的服务，但在帮助小玉挑战整个城市的过程中，陈末和管春也逐渐发现了自己躲不过的问题。从欢天喜地的生活，到惊天动地的疯狂，摆渡人最辉煌的篇章，从这里开启。","1.jpg");
INSERT INTO movie_information(name,type,director,actors,plot,pic) VALUES ("铁道飞虎","引爆笑弹","丁晟","成龙,黄子韬,王凯,王大陆,桑平,吴永伦,徐帆,池内博之,矢野浩二,张蓝心,张艺上,那威,何云伟,耿长军,刘頔,柳海龙","1941年太平洋战争爆发以后，穿过山东境内的津浦铁路成为日军在中国大陆最重要的战略交通线之一。在津浦铁路枣庄段周边，活跃着一支民间抗日游击队，这支游击队主要由枣庄火车站的几名铁路工人组成，队长叫马原，是火车站的搬运工工头。他们白天在日本人占领并管理的火车站干活，晚上出来秘密活动，利用对铁路线的熟悉以及扒火车、开火车的特殊技能，以一支小小的游击队跟大批装备精良的日本正规军周旋较量，造成了非常大的影响，当地老百姓给这支队伍起了一个响亮的名头，叫铁道飞虎队。","2.jpg");
INSERT INTO movie_information(name,type,director,actors,plot,pic) VALUES ("长城","饕餮贺岁 颠覆想象","张艺谋","马特·达蒙,景甜,佩德罗·帕斯卡,刘德华,威廉·达福,张涵予,鹿晗,彭于晏,林更新,郑恺,黄轩,陈学冬,王俊凯,余心恬,刘冰,李亨,努曼·艾克,李京沐,约翰尼·希科,皮鲁·埃斯贝克","讲述了在古代，一支中国精英部队为保卫人类，在举世闻名的长城上与怪兽饕餮进行生死决战的故事。欧洲雇佣军威廉（马特·达蒙饰）与同伴不远万里来到中国盗取火药配方，意外发现了长城是为抵御60年降临人间一次的饕餮所建。长城内部机关重重，宛如“陆上航母”，由无影禁军世代镇守。在这里，威廉见识了饕餮的凶残，也见证了无影禁军的精锐和勇敢，并被这群战士之间的信任和牺牲所感动，义无反顾地加入到了共同守护人类的战斗当中。","3.jpg");
INSERT INTO movie_information(name,type,director,actors,plot,pic) VALUES ("血战钢锯岭","唯有孤胆英雄志 枪林弹雨任翔旋","梅尔·吉布森","安德鲁·加菲尔德,雨果·维文,卢克·布雷西,泰莉莎·帕尔墨,文斯·沃恩,萨姆·沃辛顿,瑞切尔·格里菲斯,理查德·劳斯伯格,菲拉斯·迪拉尼,Luke Pegler,戈兰·克鲁特,藤井明,安倍潼,Milo Gibson,卡莎·丝黛尔马赫,纳撒尼尔·布佐尼克,马修·纳贝尔,瑞恩·科尔,James Mackay,春日博,比尔·扬,米兰· 普尔弗马克,杨枝龙田,杰里米·科斯特洛,Joshua Dean Williams,植松高菲利普·夸斯特","故事改编自二战上等兵军医戴斯蒙德·道斯的真实经历，他因为在冲绳岛战役中勇救75人生命而被授予美国国会荣誉勋章，同时也是首位获此荣誉的在战场上拒绝杀戮的医疗兵。","4.jpg");
INSERT INTO movie_information(name,type,director,actors,plot,pic) VALUES ("罗曼蒂克消亡史","被浪费的时光","程耳","葛优, 章子怡 , 浅野忠信 , 杜淳 ,钟欣潼,倪大红,赵宝刚,袁泉,闫妮,韩庚,霍思燕, 杜江 , 王传君 ,吕行,钟汉良,马晓伟,松峰莉璃,松浦敬之,平田康之,张晓龙","他一直拖到一九四九年五月初才坐上去香港的轮船，算得上真正的末班车。没有人知道他在拖什么或等待什么，我想他自己也未必知道，不过是下意识的拖延。不久他就死在香港，死前再没有值得记述的事件或说过的话，他基本 没再说话，这没什么可奇怪的，一切都不值一提，他终于走向自己的沉默。上世纪30年代的上海，叱咤风云的帮派大佬，不甘寂寞的交际花，说着地道上海话的日本妹夫，只收交通费的杀手，被冷落却忠诚的姨太太，外表光鲜的电影皇后，深宅大院里深不可测的管家，偶尔偷腥的电影皇帝，荷尔蒙满溢大脑的帮派小弟，一心想要破处的处男，善良的妓女，随波逐流的明星丈夫，投靠日本人的帮派二哥，日理万机却抽空恋爱的戴先生。战争之下，繁华落尽。帮派大佬逃亡香港，交际花不知所踪，日本妹夫死在上海，电影皇后被丈夫抛弃，处男遇上妓女，姨太太杀死二哥。战争惨烈，战争终于结束。他轻易选择沉默，因为伤口无法弥合。","5.jpg");

DROP TABLE IF EXISTS `rush_information`;
CREATE TABLE `rush_information` (
	`user_name` VARCHAR(10) NOT NULL,
	`user_num` VARCHAR(150) NOT NULL,
	PRIMARY KEY (`user_name`)
)

DROP TABLE IF EXISTS `ticket_number`;
CREATE TABLE `ticket_number` (
	`movie_name` VARCHAR(120) NOT NULL,
	`number` INT(11) NOT NULL,
	PRIMARY KEY (`movie_name`)
)

触发器：
CREATE DEFINER=`root`@`localhost` TRIGGER `trigger_movie` AFTER INSERT ON `movie_information` FOR EACH ROW insert into ticket_number(movie_name,number) values (new.name,300);

DROP TABLE IF EXISTS `user_information`;
CREATE TABLE `user_information` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_name` VARCHAR(10) NOT NULL,
	`sex` VARCHAR(5) NOT NULL,
	`email_address` VARCHAR(72) NOT NULL,
	`password` VARCHAR(32) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `user_name` (`user_name`),
	UNIQUE INDEX `email_address` (`email_address`)
)