using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models
{
    public class SemesterModel : BaseEntity
    {
        public string SemesterName { get; set; }
        public ICollection<LevelModel>? Levels  { get; set; }
        public SemesterModel()
        {
            Levels = new List<LevelModel>();
        }
    }
}
