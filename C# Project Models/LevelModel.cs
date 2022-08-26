using System.ComponentModel;
using System.ComponentModel.DataAnnotations.Schema;
using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models
{
    public class LevelModel : BaseEntity
    {
        [DisplayName("Level")]
        public string LevelName { get; set; }
        public int SemesterId { get; set; }
        public SemesterModel? Semester { get; set; }
        public ICollection<StudentModel>? Students { get; set; }
        public int? TeacherId { get; set; }
        public TeacherModel? Teacher { get; set; }
        public ICollection<ParecerModel>? Pareceres { get; set; }

        public LevelModel()
        {
            Students = new List<StudentModel>();
        }
    }

    
}
