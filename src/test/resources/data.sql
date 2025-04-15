DELETE FROM students_courses;
DELETE FROM students;

-- students テーブルにデータを挿入
INSERT INTO students (id,name, hurigana, nickname, address, area, years, gender,remark,isDeleted)
VALUES
(1,'田中 太郎', 'タナカ タロウ', 'タロ', 'tarou.tanaka@example.com','東京都葛飾区', 22, '男', '特になし',0),
(2,'鈴木 花子', 'スズキ ハナコ', 'ハナ', 'hanako.suzuki@example.com','大阪府大阪市', 24, '女', '趣味は読書',0),
(3,'佐藤 次郎', 'サトウ ジロウ', 'ジロ','zirou.satou@example.com','愛知県名古屋市', 23, '男', 'サッカーが好き',0);

-- students_courses テーブルにデータを挿入
INSERT INTO students_courses (course_id,student_id,courses, start,endDate)
VALUES
('A1',1, 'プログラミング入門', '2025-04-01 00:00:00', '2026-04-01 00:00:00'),
('B1',2,'データベース基礎', '2025-04-01 00:00:00', '2026-04-01 00:00:00'),
('C1',3,'ネットワーク技術', '2025-07-01 00:00:00', '2026-07-01 00:00:00');

