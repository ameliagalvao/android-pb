using System.ComponentModel;
using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models.ViewModels
{
    public class SemesterViewModel : BaseEntity
    {
        [DisplayName("Semester")]
        public string SemesterName { get; set; }
        public ICollection<LevelModel>? Levels  { get; set; }
}
}
