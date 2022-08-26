using System.ComponentModel;
using System.ComponentModel.DataAnnotations.Schema;
using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models
{
    public class StudentModel : BaseEntity
    {
        public string Name { get; set; }
        public ICollection<LevelModel>? Levels { get; set; }
        public ICollection<ParecerModel>? Pareceres { get; set;}

        public StudentModel()
        {
            Levels = new List<LevelModel>();
            Pareceres = new List<ParecerModel>();
        }
    }
}
