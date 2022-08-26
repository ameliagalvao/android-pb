using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DiscPareceres.Business.Models.ViewModels
{
    public class AssignedLevelData
    {
        public int LevelId { get; set; }
        public string LevelName { get; set; }
        public bool Assigned { get; set; }
        public string SemesterName { get; set; }
    }
}
