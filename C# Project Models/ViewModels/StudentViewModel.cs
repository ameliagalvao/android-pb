using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using DiscPareceres.Business.Models;
using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models.ViewModels
{
    public class StudentViewModel : BaseEntity
    {
        [Required,DisplayName("Student Name")]
        public string Name { get; set; }
        [DisplayName("Level")]
        public ICollection<AssignedLevelData>? Levels { get; set; }
        [DisplayName("Parecer Um")]
        public ICollection<ParecerModel>? PareceresUm { get; set; }
        [DisplayName("Parecer Dois")]
        public ICollection<ParecerModel>? PareceresDois { get; set; }
    }
}
