using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using StudentGrades.Models.Common;

namespace DiscPareceres.Business.Models.ViewModels
{
    public class TeacherViewModel : BaseEntity
    {
        [DisplayName("Teacher Name")]
        public string Name { get; set; }
        [DisplayName("Levels")]
        public ICollection<LevelModel>? Levels { get; set; }
    }
}
