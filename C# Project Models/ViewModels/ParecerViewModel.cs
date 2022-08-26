using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models.ViewModels
{
    public class ParecerViewModel : BaseEntity
    {
        [DisplayName("Student ID")]
        public int StudentId { get; set; }
        [DisplayName("Student")]
        public StudentModel Student { get; set; }
        [DisplayName("Level ID")]
        public int LevelId { get; set; }
        [DisplayName("Level")]
        public LevelModel Level { get; set; }
        [DisplayName("Parecer Number"), Range(1, 2)]
        public int ParecerNumber { get; set; }
        [DisplayName("Speaking Grade"), Range(0.0, 10.0)]
        public double Speaking { get; set; } = 0;
        [DisplayName("Teacher's Speaking Grade"), Range(0.0, 10.0)]
        public double TeacherSpeaking { get; set; } = 0;
        [DisplayName("Speaking Average"), Range(0.0, 10.0)]
        public double SpeakingAverage { get; set; } = 0;
        [DisplayName("Speaking Feedback")]
        public string SpeakingFeedback { get; set; }
        [DisplayName("Reading Grade"), Range(0.0, 10.0)]
        public double Reading { get; set; } = 0;
        [DisplayName("Teacher's Reading Grade"), Range(0.0, 10.0)]
        public double TeacherReading { get; set; } = 0;
        [DisplayName("Reading Average"), Range(0.0, 10.0)]
        public double ReadingAverage { get; set; } = 0;
        [DisplayName("Reading Feedback")]
        public string ReadingFeedback { get; set; }
        [DisplayName("Listening Grade"), Range(0.0, 10.0)]
        public double Listening { get; set; } = 0;
        [DisplayName("Teacher's Listening Grade"), Range(0.0, 10.0)]
        public double TeacherListening { get; set; } = 0;
        [DisplayName("Listening Average"), Range(0.0, 10.0)]
        public double ListeningAverage { get; set; } = 0;
        [DisplayName("Listening Feedback")]
        public string ListeningFeedback { get; set; }
        [DisplayName("Grammar Grade"), Range(0.0, 10.0)]
        public double Grammar { get; set; } = 0;
        [DisplayName("Teacher's Grammar Grade"), Range(0.0, 10.0)]
        public double TeacherGrammar { get; set; } = 0;
        [DisplayName("Grammar Average"), Range(0.0, 10.0)]
        public double GrammarAverage { get; set; } = 0;
        [DisplayName("Grammar Feedback")]
        public string GrammarFeedback { get; set; }
        [DisplayName("Writing Grade"), Range(0.0, 10.0)]
        public double Writing { get; set; } = 0;
        [DisplayName("Teacher's Writing Grade"), Range(0.0, 10.0)]
        public double TeacherWriting { get; set; } = 0;
        [DisplayName("Writing Average"), Range(0.0, 10.0)]
        public double WritingAverage { get; set; } = 0;
        [DisplayName("Writing Feedback")]
        public string WritingFeedback { get; set; }
        [DisplayName("Class Performance Grade"), Range(0.0, 10.0)]
        public double ClassPerformance { get; set; } = 0;
        [DisplayName("Class Performance Feedback")]
        public string ClassPerformanceFeedback { get; set; }
        [DisplayName("Parecer Grade")]
        public double ParecerGrade { get; set; } = 0;
        [DisplayName("Final Score")]
        public int FinalScore { get; set; } = 0;
        [DisplayName("Comments")]
        public string? Comments { get; set; }

    }
}
