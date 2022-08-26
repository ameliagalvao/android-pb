using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DiscPareceres.Business.Models;

namespace DiscPareceres.Business.Models.ViewModels
{
    public static class ViewModelHelpers
    {
        public static SemesterViewModel ToViewModel(this SemesterModel semesterModel)
        {
            var semesterViewModel = new SemesterViewModel()
            {
                Id = semesterModel.Id,
                SemesterName = semesterModel.SemesterName,
                Levels = semesterModel.Levels
            };

            return semesterViewModel;
        }

        public static SemesterModel ToDomainModel(this SemesterViewModel semesterViewModel)
        {
            var semesterModel = new SemesterModel()
            {
                Id = semesterViewModel.Id,
                SemesterName = semesterViewModel.SemesterName,
                Levels = semesterViewModel.Levels
            };
            return semesterModel;
        }

        public static LevelViewModel ToViewModel(this LevelModel levelModel)
        {
            var levelViewModel = new LevelViewModel()
            {
                Id = levelModel.Id,
                LevelName = levelModel.LevelName,
                SemesterId = levelModel.SemesterId,
                Semester = levelModel.Semester,
                Teacher = levelModel.Teacher,
                TeacherId = levelModel.TeacherId
            };
            return levelViewModel;
        }

        public static LevelViewModel ToViewModel(this LevelModel levelModel, ICollection<StudentModel> allDbStudentModels)
        {
            var levelViewModel = new LevelViewModel()
            {
                Id = levelModel.Id,
                LevelName = levelModel.LevelName,
                SemesterId = levelModel.SemesterId,
                Semester = levelModel.Semester,
                Teacher = levelModel.Teacher,
                TeacherId = levelModel.TeacherId
            };

            ICollection<AssignedStudentData> allStudentModels = new List<AssignedStudentData>();

            foreach (var student in allDbStudentModels)
            {
                var assignedStudent = new AssignedStudentData()
                {
                    StudentId = student.Id,
                    Name = student.Name,
                    Pareceres = student.Pareceres,
                    Assigned = levelModel.Students.FirstOrDefault(l => l.Id == student.Id) != null
                };

                allStudentModels.Add(assignedStudent);
            }

            levelViewModel.Students = allStudentModels;

            return levelViewModel;

        }

        public static LevelModel ToDomainModel(this LevelViewModel levelViewModel)
        {
            LevelModel levelModel = new LevelModel()
            {
                Id = levelViewModel.Id,
                LevelName = levelViewModel.LevelName,
                SemesterId = levelViewModel.SemesterId,
                Semester = levelViewModel.Semester,
                Teacher = levelViewModel.Teacher,
                TeacherId= levelViewModel.TeacherId
            };
            return levelModel;
        }

        public static StudentViewModel ToViewModel(this StudentModel studentModel)
        {
            var studentViewModel = new StudentViewModel
            {
                Name = studentModel.Name,
                Id = studentModel.Id,
            };

            return studentViewModel;
        }

        public static StudentViewModel ToViewModel(this StudentModel studentModel, ICollection<LevelModel> allDbLevelModels)
        {
            var studentViewModel = new StudentViewModel()
            {
                Name = studentModel.Name,
                Id = studentModel.Id,
                PareceresUm = studentModel.Pareceres.Where(p => p.ParecerNumber == 1).ToList(),
                PareceresDois = studentModel.Pareceres.Where(p => p.ParecerNumber == 2).ToList()
            };

            ICollection<AssignedLevelData> allLevelModels = new List<AssignedLevelData>();

            foreach (var level in allDbLevelModels)
            {
                var assignedLevel = level.ToAssignedLevelData();
                assignedLevel.Assigned = studentModel.Levels.FirstOrDefault(l => l.Id == level.Id) != null;
                allLevelModels.Add(assignedLevel);
            }

            studentViewModel.Levels = allLevelModels;

            return studentViewModel;

        }

        public static StudentModel ToDomainModel(this StudentViewModel studentViewModel)
        {
            var studentModel = new StudentModel()
            {
                Name = studentViewModel.Name,
                Id = studentViewModel.Id
            };
            
            return studentModel;
        }

        public static AssignedLevelData ToAssignedLevelData(this LevelModel levelModel)
        {
            var assignedLevelData = new AssignedLevelData()
            {
                LevelId = levelModel.Id,
                LevelName = levelModel.LevelName,
                Assigned = false,
                SemesterName = levelModel.Semester.SemesterName
            };
            return assignedLevelData;
        }

        public static ParecerModel ToDomainModel (this ParecerViewModel parecerViewModel)
        {
            var parecerUmModel = new ParecerModel()
            {
                Id = parecerViewModel.Id,
                ParecerNumber = parecerViewModel.ParecerNumber,
                LevelId = parecerViewModel.LevelId,
                StudentId = parecerViewModel.StudentId,
                Reading = parecerViewModel.Reading,
                TeacherReading = parecerViewModel.TeacherReading,
                Grammar = parecerViewModel.Grammar,
                TeacherGrammar = parecerViewModel.TeacherGrammar,
                Speaking = parecerViewModel.Speaking,
                TeacherSpeaking = parecerViewModel.TeacherSpeaking,
                Listening = parecerViewModel.Listening,
                TeacherListening = parecerViewModel.TeacherListening,
                Writing = parecerViewModel.Writing,
                TeacherWriting = parecerViewModel.TeacherWriting,
                ClassPerformance = parecerViewModel.ClassPerformance,
                Comments = parecerViewModel.Comments
            };
            return parecerUmModel;
        }

        public static ParecerViewModel ToViewModel(this ParecerModel parecerModel)
        {
            var parecerViewModel = new ParecerViewModel()
            {
                Id = parecerModel.Id,
                Student = parecerModel.Student,
                StudentId = parecerModel.StudentId,
                Level = parecerModel.Level,
                LevelId = parecerModel.LevelId,
                Reading = parecerModel.Reading,
                ReadingAverage = parecerModel.ReadingAverage,
                ReadingFeedback = parecerModel.ReadingFeedback,
                TeacherReading = parecerModel.TeacherReading,
                Grammar = parecerModel.Grammar,
                TeacherGrammar = parecerModel.TeacherGrammar,
                GrammarAverage = parecerModel.GrammarAverage,
                GrammarFeedback = parecerModel.GrammarFeedback,
                Speaking = parecerModel.Speaking,
                TeacherSpeaking = parecerModel.TeacherSpeaking,
                SpeakingAverage = parecerModel.SpeakingAverage,
                SpeakingFeedback = parecerModel.SpeakingFeedback,
                Listening = parecerModel.Listening,
                TeacherListening = parecerModel.TeacherListening,
                ListeningAverage = parecerModel.ListeningAverage,
                ListeningFeedback = parecerModel.ListeningFeedback,
                Writing = parecerModel.Writing,
                TeacherWriting = parecerModel.TeacherWriting,
                WritingAverage = parecerModel.WritingAverage,
                WritingFeedback = parecerModel.WritingFeedback,
                ClassPerformance = parecerModel.ClassPerformance,
                ClassPerformanceFeedback = parecerModel.ClassPerformanceFeedback,
                ParecerGrade = parecerModel.ParecerGrade,
                ParecerNumber = parecerModel.ParecerNumber,
                FinalScore = parecerModel.GetFinalGrade(parecerModel),
                Comments = parecerModel.Comments
            };
            return parecerViewModel;
        }

        public static TeacherViewModel ToViewModel(this TeacherModel teacherModel)
        {
            var teacherViewModel = new TeacherViewModel()
            {
                Id = teacherModel.Id,
                Name = teacherModel.Name,
                Levels = teacherModel.Levels
            };
            return teacherViewModel;
        }

        public static TeacherModel ToDomainModel(this TeacherViewModel teacherViewModel)
        {
            var teacherModel = new TeacherModel()
            {
                Id = teacherViewModel.Id,
                Name = teacherViewModel.Name,
                Levels = teacherViewModel.Levels
            };
            return teacherModel;
        }


    }
}
