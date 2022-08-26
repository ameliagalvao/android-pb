using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DiscPareceres.Business.Models.ViewModels
{
    public class AssignedStudentData
    {
        public int StudentId { get; set; }
        public string Name { get; set; }
        public bool Assigned { get; set; }
        public ICollection<ParecerModel>? Pareceres { get; set; }
    }
}
