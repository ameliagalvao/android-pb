using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models
{
    public class TeacherModel : BaseEntity
    {
        public string Name { get; set; }
        public ICollection<LevelModel>? Levels { get; set; }
    }
}
