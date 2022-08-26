using System.ComponentModel;
using System.ComponentModel.DataAnnotations.Schema;
using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models.ViewModels
{
    public class LevelViewModel : BaseEntity
    {
        [DisplayName("Level Name")]
        public string LevelName { get; set; }
        [DisplayName("Semester ID")]
        public int SemesterId { get; set; }
        [DisplayName("Semester")]
        public SemesterModel? Semester { get; set; }
        [DisplayName("Students")]
        public ICollection<AssignedStudentData>? Students { get; set; }
        [DisplayName("Teacher ID")]
        public int? TeacherId { get; set; }
        [DisplayName("Teacher")]
        public TeacherModel? Teacher { get; set; }
    }
}
